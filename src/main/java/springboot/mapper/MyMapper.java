package springboot.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * MyMapper
 *
 * @author ljh
 * created on 2022/11/1 16:23
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
