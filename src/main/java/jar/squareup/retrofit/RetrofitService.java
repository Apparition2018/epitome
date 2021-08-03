package jar.squareup.retrofit;

import l.demo.Person;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * RetrofitService
 *
 * @author ljh
 * created on 2021/8/3 17:57
 */
public interface RetrofitService {
    @GET("demo/get")
    Call<Person.Student> get(@Query("id") Integer id, @Query("name") String name);
}
