package jar.squareup.retrofit;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;
import l.demo.Demo;
import l.demo.Person;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Retrofit
 * https://square.github.io/retrofit/
 *
 * @author ljh
 * @since 2021/8/3 17:49
 */
public class RetrofitDemo extends Demo {

    @Test
    public void testRetrofit() throws IOException {
        Moshi moshi = new Moshi.Builder().add(new CustomDateAdapter()).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        Response<Person.Student> response = service.get(1, MY_NAME).execute();
        p(response.body());
    }

    static class CustomDateAdapter {
        @ToJson
        private String dateToJson(Date d) {
            return DATE_TIME_FORMAT.get().format(d);
        }

        @FromJson
        private Date dateFromJson(String s) throws ParseException {
            return DATE_TIME_FORMAT.get().parse(s);
        }
    }
}
