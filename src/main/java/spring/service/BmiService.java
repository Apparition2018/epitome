package spring.service;

import org.springframework.stereotype.Service;

/**
 * BmiService
 *
 * @author Arsenal
 * created on 2020/11/26 2:13
 */
@Service
public class BmiService {

    public String bmi(double height, double weight) {
        String status = "正常";
        // 计算bmi指数
        double bmi = weight / height / height;
        // 依据bmi指数，判断用户身体状况
        if (bmi < 19) {
            status = "过轻";
        }
        if (bmi > 25) {
            status = "过重";
        }
        return status;
    }
}
