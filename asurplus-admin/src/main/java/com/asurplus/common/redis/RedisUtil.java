package com.asurplus.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Redis操作工具
 *
 * @Author Lizhou
 **/
@Component
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入数据
     *
     * @param key   键值
     * @param value 数据
     */
    public <T> void set(final String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 批量写入数据
     *
     * @param map 键-值 集合
     */
    public void setBatch(Map<String, Object> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    /**
     * 写入数据
     *
     * @param key     键值
     * @param value   数据
     * @param seconds 存活时间（默认秒）
     */
    public <T> void set(final String key, T value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * 写入数据
     *
     * @param key      键值
     * @param value    数据
     * @param seconds  存活时间（默认秒）
     * @param timeUnit 时间颗粒度
     */
    public <T> void set(final String key, T value, long seconds, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, seconds, timeUnit);
    }

    /**
     * 设置存活时间
     *
     * @param key     键值
     * @param seconds 过期时间（默认秒）
     */
    public boolean setExpire(final String key, long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 设置存活时间
     *
     * @param key      键值
     * @param seconds  存活时间
     * @param timeUnit 时间颗粒度
     * @return
     */
    public boolean setExpire(final String key, long seconds, TimeUnit timeUnit) {
        return redisTemplate.expire(key, seconds, timeUnit);
    }

    /**
     * 获取存活时间
     *
     * @param key 键值
     * @return
     */
    public long getExpire(final String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 获取存活时间
     *
     * @param key      键值
     * @param timeUnit 时间颗粒度
     * @return
     */
    public long getExpire(final String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 移除存活时间
     *
     * @param key 键值
     * @return
     */
    public boolean removeExpire(final String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 获取数据
     *
     * @param key 键值
     * @param <T> 数据类型
     * @return
     */
    public <T> T get(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 获取并删除
     *
     * @param key 键值
     * @param <T> 数据类型
     * @return
     */
    public <T> T getAndDelete(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        // 获取
        T t = operation.get(key);
        // 删除
        delete(key);
        return t;
    }

    /**
     * 删除数据
     *
     * @param key 键值
     * @return
     */
    public boolean delete(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除数据
     *
     * @param keys 键值集合
     * @return
     */
    public long delete(final Collection keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 设置新值，返回旧值
     *
     * @param key   键值
     * @param value 新数据
     * @param <T>   数据类型
     * @return 旧值
     */
    public <T> Object getAndSet(final String key, final T value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键值
     * @return
     */
    public boolean isExist(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据前缀获取所有key
     *
     * @param prefix 前缀
     * @return
     */
    public Set<String> getKeys(String prefix) {
        return redisTemplate.keys(prefix.concat("*"));
    }

    /*******************************************************List操作*******************************************************/

    /**
     * 往左边写入List数据
     *
     * @param key   键值
     * @param value List集合
     * @param <T>   数据类型
     * @return
     */
    public <T> long setListLeft(final String key, final List<T> value) {
        Long rows = redisTemplate.opsForList().leftPushAll(key, value);
        return null == rows ? 0 : rows;
    }

    /**
     * 往右边写入List数据
     *
     * @param key   键值
     * @param value List集合
     * @param <T>   数据类型
     * @return
     */
    public <T> long setListRight(final String key, final List<T> value) {
        Long rows = redisTemplate.opsForList().rightPushAll(key, value);
        return null == rows ? 0 : rows;
    }

    /**
     * 往左边写入一个元素
     *
     * @param key   键值
     * @param value 数据
     * @param <T>   数据类型
     * @return
     */
    public <T> long setListLeft(final String key, final T value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 往右边写入一个元素
     *
     * @param key   键值
     * @param value 数据
     * @param <T>   数据类型
     * @return
     */
    public <T> long setListRight(final String key, final T value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 获取List数据
     *
     * @param key 键值
     * @param <T> 数据类型
     * @return
     */
    public <T> List<T> getList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 从List左边弹出一个元素
     *
     * @param key 键值
     * @param <T> 数据类型
     * @return
     */
    public <T> T getListLeft(final String key) {
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
        return listOperations.leftPop(key);
    }

    /**
     * 从List右边弹出一个元素
     *
     * @param key 键值
     * @param <T> 数据类型
     * @return
     */
    public <T> T getListRight(final String key) {
        ListOperations<String, T> listOperations = redisTemplate.opsForList();
        return listOperations.rightPop(key);
    }

    /*******************************************************Set操作*******************************************************/

    /**
     * 写入Set数据
     *
     * @param key   键值
     * @param value Set集合
     * @param <T>   数据类型
     * @return
     */
    public <T> BoundSetOperations<String, T> setSet(final String key, final Set<T> value) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = value.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获取Set数据
     *
     * @param key 键值
     * @param <T> 数据类型
     * @return
     */
    public <T> Set<T> getSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /*******************************************************Map操作*******************************************************/

    /**
     * 写入Map数据
     *
     * @param key   键值
     * @param value Map集合
     * @param <T>   数据类型
     */
    public <T> void setMap(final String key, final Map<String, T> value) {
        redisTemplate.opsForHash().putAll(key, value);
    }

    /**
     * 获取Map数据
     *
     * @param key 键值
     * @param <T> 数据类型
     * @return
     */
    public <T> Map<String, T> getMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 往Hash中写入数据
     *
     * @param key   键值
     * @param hKey  hash键值
     * @param value 数据
     * @param <T>   数据类型
     */
    public <T> void setMap(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  键值
     * @param hKey hash键值
     * @param <T>  数据类型
     * @return
     */
    public <T> T getMap(final String key, final String hKey) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key   键值
     * @param hKeys hash键值集合
     * @param <T>   数据类型
     * @return
     */
    public <T> List<T> getMap(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 删除Hash中的数据
     *
     * @param key  键值
     * @param hKey hash键值集合
     * @return
     */
    public long deleteMap(final String key, final String hKey) {
        return redisTemplate.opsForHash().delete(key, hKey);
    }

    /*******************************************************数值操作*******************************************************/

    /**
     * 数字相加
     *
     * @param key   键值
     * @param value 数字
     * @return
     */
    public long add(final String key, final Long value) {
        return redisTemplate.opsForValue().increment(key, value);
    }

    /**
     * 数字相加1
     *
     * @param key 键值
     * @return
     */
    public long add1(final String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 数字相减
     *
     * @param key   键值
     * @param value 数字
     * @return
     */
    public long sub(final String key, final Long value) {
        return redisTemplate.opsForValue().decrement(key, value);
    }

    /**
     * 数字相减1
     *
     * @param key 键值
     * @return
     */
    public long sub1(final String key) {
        return redisTemplate.opsForValue().decrement(key);
    }
}
