package springboot.mapper.master.tk;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * MyMapper
 *
 * @author ljh
 * @since 2022/11/1 16:23
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
