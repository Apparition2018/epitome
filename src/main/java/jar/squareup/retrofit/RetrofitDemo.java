package jar.squareup.retrofit;

import l.demo.Person;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;

/**
 * Retrofit
 * https://square.github.io/retrofit/
 *
 * @author ljh
 * created on 2021/8/3 17:49
 */
public class RetrofitDemo {

    @Test
    public void testRetrofit() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:3333/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Response<Person.Student> response = service.get(1, "ljh").execute();
        System.out.println(response);
    }
}
