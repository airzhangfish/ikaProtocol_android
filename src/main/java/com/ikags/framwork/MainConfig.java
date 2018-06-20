package com.ikags.framwork;




import com.ikags.framwork.web.WebProtocol;
import com.ikags.ikaprotocol.appprotocol.AppWebProtocol;
import com.ikags.ikaprotocol.appprotocol.NativetestProtocol;
import com.ikags.ikaprotocol.appprotocol.TelMailProtocol;

import java.util.ArrayList;

/**
 * 配置文件
 */

public class MainConfig {


    public static String defaultWebActivity = "com.ikags.ikaprotocol.WebShowActivity";

    public static ArrayList protocollist = new ArrayList<WebProtocol>();
    public static ArrayList<WebProtocol> getWebProtocol() {
        if(protocollist!=null && protocollist.isEmpty()){

            protocollist.add(new AppWebProtocol());
            protocollist.add(new NativetestProtocol());
            protocollist.add(new TelMailProtocol());

        }
        return protocollist;
    }

}
