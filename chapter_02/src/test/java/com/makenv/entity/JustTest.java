package com.makenv.entity;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class JustTest {

    public static void main(String[] args) {

        //加密算法
        String hashAlgorithmName = "MD5";
        //凭证
        String credentials = "123";
        //随机数为盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println("盐为 " + salt);
        int hashIterations = 16;

        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        //这个是加密后的admin的密码
        System.out.println("加密后的密码为 " + result);
    }

    @Test
    public void test1() {
        ResourceEntity resourceEntity = new ResourceEntity();
//        resourceEntity.setType("menu");
        System.out.println(ResourceEntity.ResourceType.menu);
    }


}
