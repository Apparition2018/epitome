package jar.hutool.system;

import cn.hutool.system.oshi.OshiUtil;

import static l.demo.Demo.p;

/**
 * <a href="https://apidoc.gitee.com/dromara/hutool/cn/hutool/system/oshi/OshiUtil.html">OshiUtil</a>   获取系统和硬件信息工具
 * <p>需要引入 com.github.oshi:oshi-core
 * <p><a href="https://www.cnblogs.com/weechang/p/12493978.html">使用 oshi 获取主机信息</a>
 *
 * @author ljh
 * @since 2020/11/19 14:39
 */
public class OshiUtilDemo {

    public static void main(String[] args) {
        // 操作系统
        p(OshiUtil.getOs());            // Microsoft Windows 10.0 build 18363
        // BIOS
        p(OshiUtil.getSystem());        // manufacturer=LENOVO, model=90CYA00YCN, serial number=P502GSP2
        // 内存
        p(OshiUtil.getMemory());        // Available: 1.9 GiB/7.9 GiB
        // 磁盘
        p(OshiUtil.getDiskStores());    // [\\.\PHYSICALDRIVE0: (model: ST1000DM003-1ER162 (标准磁盘驱动器) - S/N:             Z4YCFTFH) size: 1.0 TB, reads: 4496234 (108.7 GiB), writes: 4616345 (166.9 GiB), xfer: 1605632518143]
        // 硬件
        p(OshiUtil.getHardware());      // oshi.hardware.platform.windows.WindowsHardwareAbstractionLayer@55b53d44

        // CPU
        p(OshiUtil.getCpuInfo());
        // CpuInfo{cpu核心数=4, CPU总的使用率=4440.0, CPU系统使用率=5.65, CPU用户使用率=19.35, CPU当前等待率=0.0, CPU当前空闲率=75.0, CPU利用率=25.0, CPU型号信息='Intel(R) Core(TM) i5-4590 CPU @ 3.30GHz
        //  1 physical CPU package(s)
        //  4 physical CPU core(s)
        //  4 logical CPU(s)
        // Identifier: Intel64 Family 6 Model 60 Stepping 3
        // ProcessorID: BFEBFBFF000306C3
        // Microarchitecture: Haswell (Client)'}

        // CPU
        p(OshiUtil.getProcessor());
        // Intel(R) Core(TM) i5-4590 CPU @ 3.30GHz
        //  1 physical CPU package(s)
        //  4 physical CPU core(s)
        //  4 logical CPU(s)
        // Identifier: Intel64 Family 6 Model 60 Stepping 3
        // ProcessorID: BFEBFBFF000306C3
        // Microarchitecture: Haswell (Client)

        // 网络
        p(OshiUtil.getNetworkIFs());
        // [Name: eth4 (Realtek PCIe GBE Family Controller)
        //   MAC Address: 4c:cc:6a:13:0f:31
        //   MTU: 1500, Speed: 1000000000
        //   IPv4: [192.168.8.223/23]
        //   IPv6: [fe80:0:0:0:c582:686f:2b98:a92b/64]
        //   Traffic: received 89940 packets/98.3 MiB (0 err, 0 drop); transmitted 63976 packets/10.9 MiB (0 err, 0 coll);]

        // 传感器
        p(OshiUtil.getSensors());
        // ...... Error was -2147217405: Failed to enumerate results.
        // CPU Temperature=0.0°C, Fan Speeds=[], CPU Voltage=0.0
    }
}
