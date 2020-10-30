package jar.hutool;

import cn.hutool.core.net.NetUtil;
import l.demo.Demo;
import org.junit.Test;

/**
 * NetUtilDemo  网络工具
 * https://hutool.cn/docs/#/core/%E7%BD%91%E7%BB%9C/%E7%BD%91%E7%BB%9C%E5%B7%A5%E5%85%B7-NetUtil
 * https://apidoc.gitee.com/loolly/hutool/cn/hutool/core/net/NetUtil.html
 *
 * @author ljh
 * created on 2020/10/30 17:16
 */
public class NetUtilDemo extends Demo {
    
    @Test
    public void testNetUtil() {
        // 本机网卡IP地址
        p(NetUtil.getLocalhost());          // JS3-LJH/192.168.8.223
        // 主机名称
        p(NetUtil.getLocalHostName());      // JS3-LJH
        // 本机网卡IP地址
        p(NetUtil.getLocalhostStr());       // 192.168.8.223
        // 本机MAC地址
        p(NetUtil.getLocalMacAddress());    // 4c-cc-6a-13-0f-31
        
        // 通过域名得到IP
        p(NetUtil.getIpByHost(BAIDU_HOST)); // 14.215.177.38
    }
}
