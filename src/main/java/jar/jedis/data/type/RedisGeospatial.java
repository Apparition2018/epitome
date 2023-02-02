package jar.jedis.data.type;

import com.google.common.collect.Maps;
import jar.jedis.JedisUtils;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.args.GeoUnit;
import redis.clients.jedis.params.GeoAddParams;
import redis.clients.jedis.params.GeoRadiusParam;

import java.util.HashMap;

import static l.demo.Demo.p;

/**
 * <a href="https://redis.io/docs/data-types/geospatial/">Redis Geospatial</a>
 * <p>坐标
 *
 * @author ljh
 * @since 2022/8/16 2:32
 */
public class RedisGeospatial {

    static class Map {
        private static final String MAP_GD_KEY = "map:gd";

        @Test
        public void testMap() {
            Jedis jedis = JedisUtils.getResource();
            // 添加地理空间项（经度、维度、名称）
            jedis.geoadd(MAP_GD_KEY, GdEnum.ZHONG_SHAN.getGeoCoordinate().getLongitude(),
                    GdEnum.ZHONG_SHAN.getGeoCoordinate().getLatitude(), GdEnum.ZHONG_SHAN.getName());
            HashMap<String, GeoCoordinate> memberCoordinateMap = Maps.newHashMap();
            memberCoordinateMap.put(GdEnum.GUANG_ZHOU.getName(), GdEnum.GUANG_ZHOU.getGeoCoordinate());
            memberCoordinateMap.put(GdEnum.SHEN_ZHEN.getName(), GdEnum.SHEN_ZHEN.getGeoCoordinate());
            memberCoordinateMap.put(GdEnum.FO_SHAN.getName(), GdEnum.FO_SHAN.getGeoCoordinate());
            memberCoordinateMap.put(GdEnum.ZHU_HAI.getName(), GdEnum.ZHU_HAI.getGeoCoordinate());
            // 批量添加地理空间项（经度、维度、名称）
            jedis.geoadd(MAP_GD_KEY, GeoAddParams.geoAddParams(), memberCoordinateMap);
            // 返回经纬度
            jedis.geopos(MAP_GD_KEY, GdEnum.ZHONG_SHAN.getName(), GdEnum.GUANG_ZHOU.getName()).forEach(System.out::println);
            // 返回距离
            p(jedis.geodist(MAP_GD_KEY, GdEnum.ZHONG_SHAN.getName(), GdEnum.GUANG_ZHOU.getName(), GeoUnit.KM));
            // 返回 GeoHash
            p(jedis.geohash(MAP_GD_KEY, GdEnum.ZHONG_SHAN.getName(), GdEnum.GUANG_ZHOU.getName()));
            // 返回指定经纬度半径内成员
            jedis.georadius(MAP_GD_KEY, 113.5D, 22.5D, 100D, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending())
                    .forEach(geoRadiusResponse -> System.out.printf("地点：%s，坐标：%s，距离：%s%n", new String(geoRadiusResponse.getMember()), geoRadiusResponse.getCoordinate(), geoRadiusResponse.getDistance()));
            // 返回指定成员半径内成员
            jedis.georadiusByMember(MAP_GD_KEY, GdEnum.ZHONG_SHAN.getName(), 100D, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending())
                    .forEach(geoRadiusResponse -> System.out.printf("地点：%s，坐标：%s，距离：%s%n", new String(geoRadiusResponse.getMember()), geoRadiusResponse.getCoordinate(), geoRadiusResponse.getDistance()));
        }
    }

    @Getter
    private enum GdEnum {
        GUANG_ZHOU("广州", new GeoCoordinate(113.280637D, 23.125178D)),
        SHEN_ZHEN("深圳", new GeoCoordinate(114.085947D, 22.547D)),
        FO_SHAN("佛山", new GeoCoordinate(113.122717D, 23.028762D)),
        ZHONG_SHAN("中山", new GeoCoordinate(113.382391D, 22.521113D)),
        ZHU_HAI("珠海", new GeoCoordinate(113.552724D, 22.255899D));

        private final String name;
        private final GeoCoordinate geoCoordinate;

        GdEnum(String name, GeoCoordinate geoCoordinate) {
            this.name = name;
            this.geoCoordinate = geoCoordinate;
        }
    }
}
