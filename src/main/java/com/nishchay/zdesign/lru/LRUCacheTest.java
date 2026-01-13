package com.nishchay.zdesign.lru;

public class LRUCacheTest {

    public static void main(String[] args) {
        cacheDemo();
        cacheDemo1();
        oldCacheDemo();
    }

    private static void cacheDemo() {

        LRUCache<Integer, Integer> lruCache = new LRUCache<>(3);
        lruCache.put(1, 100);
        lruCache.put(10, 99);
        lruCache.put(15, 98);
        System.out.println("lruCache = " + lruCache);
        lruCache.put(10, 97);
        System.out.println("lruCache = " + lruCache);
        lruCache.put(12, 96);
        System.out.println("lruCache = " + lruCache);
        lruCache.put(18, 95); // remove - Node{key=1, value=100} & add - Node{key=18, value=95}
        System.out.println("lruCache = " + lruCache);
        lruCache.get(15);
        lruCache.put(1, 94);// remove - Node{key=10, value=97} & add - Node{key=1, value=94}
        System.out.println("lruCache = " + lruCache);

        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(10));
        System.out.println(lruCache.get(15));
    }

    private static void cacheDemo1() {

        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "Object1");
        lruCache.put(2, "Object2");
        lruCache.put(3, "Object3");
        System.out.println("lruCache = " + lruCache);
        System.out.println("lruCache.get(1) - " + lruCache.get(1));
        System.out.println("lruCache.get(2) - " + lruCache.get(2));
        System.out.println("lruCache.get(3) - " + lruCache.get(3));

        System.out.println("-----------------------");
        System.out.println("lruCache = " + lruCache);
        lruCache.put(4, "Object4");
        System.out.println("lruCache = " + lruCache);
        System.out.println("lruCache.get(2) - " + lruCache.get(2));

        System.out.println("lruCache = " + lruCache);
        lruCache.put(5, "Object5");
        System.out.println("lruCache = " + lruCache);
        System.out.println("lruCache.get(3) - " + lruCache.get(3));
        System.out.println("lruCache.get(1) - " + lruCache.get(1));

        lruCache.put(6, "Object6");
        System.out.println("lruCache = " + lruCache);

        System.out.println("-----------------------");
        System.out.println("lruCache.get(4) - " + lruCache.get(4));
        System.out.println("lruCache.get(5) - " + lruCache.get(5));
        System.out.println("lruCache.get(6) - " + lruCache.get(6));

        System.out.println("-----------------------");
        System.out.println(lruCache);
        lruCache.put(7, "Object7");
        System.out.println(lruCache);
        lruCache.put(1, "Object10");
        System.out.println(lruCache);
        System.out.println("lruCache.get(1) - " + lruCache.get(1));
    }

    private static void oldCacheDemo() {

        LRUCacheOld<Integer, Integer> lruCache = new LRUCacheOld<>(3);
        lruCache.put(1, 100);
        lruCache.put(10, 99);
        lruCache.put(15, 98);
        System.out.println("lruCache = " + lruCache);
        lruCache.put(10, 97);
        System.out.println("lruCache = " + lruCache);
        lruCache.put(12, 96);
        System.out.println("lruCache = " + lruCache);
        lruCache.put(18, 95); // remove - Node{key=1, value=100} & add - Node{key=18, value=95}
        System.out.println("lruCache = " + lruCache);
        lruCache.get(15);
        lruCache.put(1, 94);// remove - Node{key=10, value=97} & add - Node{key=1, value=94}
        System.out.println("lruCache = " + lruCache);

        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(10));
        System.out.println(lruCache.get(15));
    }

}

/*
 *	O/P =>
 *
 * lruCache = LRUCache{map={1=1->100, 10=10->99, 15=15->98}}
 * lruCache = LRUCache{map={1=1->100, 10=10->97, 15=15->98}}
 * lruCache = LRUCache{map={12=12->96, 10=10->97, 15=15->98}}
 * lruCache = LRUCache{map={12=12->96, 10=10->97, 18=18->95}}
 * lruCache = LRUCache{map={12=12->96, 1=1->94, 18=18->95}}
 * 94
 * null
 * null
 *
 *
 *
 *	lruCache = LRUCache{ head => 3->Object3, 2->Object2, 1->Object1 }
 *	lruCache.get(1) - Object1
 *	lruCache.get(2) - Object2
 *	lruCache.get(3) - Object3
 *	-----------------------
 *	lruCache = LRUCache{ head => 3->Object3, 2->Object2, 1->Object1 }
 *	lruCache = LRUCache{ head => 4->Object4, 3->Object3, 2->Object2 }
 *	lruCache.get(2) - Object2
 *	lruCache = LRUCache{ head => 2->Object2, 4->Object4, 3->Object3 }
 *	lruCache = LRUCache{ head => 5->Object5, 2->Object2, 4->Object4 }
 *	lruCache.get(3) - null
 *	lruCache.get(1) - null
 *	lruCache = LRUCache{ head => 6->Object6, 5->Object5, 2->Object2 }
 *	-----------------------
 *	lruCache.get(4) - null
 *	lruCache.get(5) - Object5
 *	lruCache.get(6) - Object6
 *	-----------------------
 *	LRUCache{ head => 6->Object6, 5->Object5, 2->Object2 }
 *	LRUCache{ head => 7->Object7, 6->Object6, 5->Object5 }
 *	LRUCache{ head => 1->Object10, 7->Object7, 6->Object6 }
 *	lruCache.get(1) - Object10
 * */