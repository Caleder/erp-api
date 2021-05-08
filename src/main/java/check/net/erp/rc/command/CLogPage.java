package check.net.erp.rc.command;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import check.net.erp.base.BaseCommand;
import check.net.erp.base.Param;
import check.net.erp.base.StrKit;
import check.net.erp.base.command.Command;
import check.net.erp.constant.Define;
import check.net.erp.rc.model.Log;
import check.net.erp.rc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 日志分页列表
 */
@Command
public class CLogPage extends BaseCommand {

    @Autowired
    private LogService logService;

    @Param(defaultValue = "{}")
    private JSONObject query; // 查询对象
    @Param(defaultValue = Define.CURRENT)
    private long current;
    @Param(defaultValue = Define.SIZE)
    private long size;

    @Override
    protected void init() throws Exception {
        String startTime = query.getString("startTime");
        if (StrKit.notBlank(startTime)) {
            query.put("startTime", startTime + " 00:00:00");
        }

        String endTime = query.getString("endTime");
        if (StrKit.notBlank(endTime)) {
            query.put("endTime", endTime + " 23:59:59");
        }
    }

    @Override
    protected void doCommand() throws Exception {
        Page<Log> logPage = logService.pageSearch(current, size, query);

        data.put("logPage", logPage);
    }

}
