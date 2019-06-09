package com.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Louis
 * @title: RedisUtil
 * @projectName springboot-study
 * @description: TODO Redis工具类
 * @date 2019/6/9 15:56
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param [key, time]
     * @return boolean
     * @description: //TODO 指定缓存失效时间
     * @author Louis
     * @date 2019/6/9 15:57
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key]
     * @return long
     * @description: //TODO 根据key获取过期时间
     * @author Louis
     * @date 2019/6/9 15:58
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * @param [key]
     * @return boolean
     * @description: //TODO 判断key是否存在
     * @author Louis
     * @date 2019/6/9 15:58
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key]
     * @return void
     * @description: //TODO 删除缓存
     * @author Louis
     * @date 2019/6/9 15:59
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * @param [key]
     * @return java.lang.Object
     * @description: //TODO 普通缓存获取
     * @author Louis
     * @date 2019/6/9 15:59
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * @param [key, value]
     * @return boolean
     * @description: //TODO 普通缓存放入
     * @author Louis
     * @date 2019/6/9 15:59
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, value, time]
     * @return boolean
     * @description: //TODO 普通缓存放入并设置时间
     * @author Louis
     * @date 2019/6/9 16:00
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, delta]
     * @return long
     * @description: //TODO 递增
     * @author Louis
     * @date 2019/6/9 16:00
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * @param [key, delta]
     * @return long
     * @description: //TODO 递减
     * @author Louis
     * @date 2019/6/9 16:01
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }


    /**
     * @param [key, item]
     * @return java.lang.Object
     * @description: //TODO HashGet 用于返回哈希表中指定字段的值
     * @author Louis
     * @date 2019/6/9 16:01
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * @param [key]
     * @return Map<Object, Object>
     * @description: //TODO 获取hashKey对应的所有键值
     * @author Louis
     * @date 2019/6/9 16:03
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * @param [key, map]
     * @return boolean
     * @description: //TODO 用于同时将多个 field-value (字段-值)对设置到哈希表中。
     * @author Louis
     * @date 2019/6/9 16:03
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, map, time]
     * @return boolean
     * @description: //TODO 用于同时将多个 field-value (字段-值)对设置到哈希表中。并设置时间
     * @author Louis
     * @date 2019/6/9 16:04
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, item, value]
     * @return boolean
     * @description: //TODO 向一张hash表中放入数据,如果不存在将创建
     * @author Louis
     * @date 2019/6/9 16:04
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, item, value, time]
     * @return boolean
     * @description: //TODO 向一张hash表中放入数据,如果不存在将创建
     * @author Louis
     * @date 2019/6/9 16:05
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, item]
     * @return void
     * @description: //TODO 删除hash表中的值
     * @author Louis
     * @date 2019/6/9 16:05
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * @param [key, item]
     * @return boolean
     * @description: //TODO 判断hash表中是否有该项的值
     * @author Louis
     * @date 2019/6/9 16:05
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * @param [key, item, by]
     * @return double
     * @description: //TODO hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @author Louis
     * @date 2019/6/9 16:06
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * @param [key, item, by]
     * @return double
     * @description: //TODO hash递减
     * @author Louis
     * @date 2019/6/9 16:06
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * @param [key]
     * @return java.util.Set<java.lang.Object>
     * @description: //TODO 根据key获取Set中的所有值
     * @author Louis
     * @date 2019/6/9 16:21
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param [key, value]
     * @return boolean
     * @description: //TODO 根据value从一个set中查询,是否存在
     * @author Louis
     * @date 2019/6/9 16:21
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, values]
     * @return long
     * @description: //TODO 将数据放入set缓存
     * @author Louis
     * @date 2019/6/9 16:21
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param [key, time, values]
     * @return long
     * @description: //TODO 将set数据放入缓存
     * @author Louis
     * @date 2019/6/9 16:21
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param [key]
     * @return long
     * @description: //TODO 获取set缓存的长度
     * @author Louis
     * @date 2019/6/9 16:21
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param [key, values]
     * @return long
     * @description: //TODO 移除值为value的
     * @author Louis
     * @date 2019/6/9 16:21
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    // ===============================list=================================

    /**
     * @param [key, start, end]
     * @return java.util.List<java.lang.Object>
     * @description: //TODO 获取list缓存的内容
     * @author Louis
     * @date 2019/6/9 16:20
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param [key]
     * @return long
     * @description: //TODO 获取list缓存的长度
     * @author Louis
     * @date 2019/6/9 16:20
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param [key, index]
     * @return java.lang.Object
     * @description: //TODO 通过索引 获取list中的值
     * @author Louis
     * @date 2019/6/9 16:20
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param [key, value]
     * @return boolean
     * @description: //TODO 将list放入缓存
     * @author Louis
     * @date 2019/6/9 16:20
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, value, time]
     * @return boolean
     * @description: //TODO 将list放入缓存
     * @author Louis
     * @date 2019/6/9 16:20
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, value]
     * @return boolean
     * @description: //TODO 将list放入缓存
     * @author Louis
     * @date 2019/6/9 16:20
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, value, time]
     * @return boolean
     * @description: //TODO 将list放入缓存
     * @author Louis
     * @date 2019/6/9 16:20
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, index, value]
     * @return boolean
     * @description: //TODO 根据索引修改list中的某条数据
     * @author Louis
     * @date 2019/6/9 16:19
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param [key, count, value]
     * @return long
     * @description: //TODO 移除N个为value的值
     * @author Louis
     * @date 2019/6/9 16:19
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
