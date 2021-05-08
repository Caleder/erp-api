package check.net.erp.bc.command;

import check.net.erp.base.command.Command;
import check.net.erp.base.BaseCommand;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 创建新的编号
 */
@Command
public class CRefundCreateCode extends BaseCommand {

    private static SimpleDateFormat codeFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static Random random = new Random();

    @Override
    protected void init() throws Exception {

    }

    @Override
    protected void doCommand() throws Exception {
        String code = "RL" + codeFormatter.format(new Date()) + random.nextInt(10) + random.nextInt(10);

        data.put("code", code);
    }

}
