package com.joking.netty.buffer;

import java.nio.IntBuffer;

/**
 * @author joking
 */
public class UseBuffer {

    static IntBuffer intBuffer = null;

    public static void main(String[] args) {
        allocate(10);
    }

    public static void allocate(int size) {
        intBuffer = IntBuffer.allocate(size);

        for (int i = 0; i < 5; i++) {
            if(i == 2) {
                intBuffer.mark();
            }
            intBuffer.put(i + 5);
        }

        intBuffer.reset();

        System.out.println("position: " + intBuffer.position());
        System.out.println("limit: " + intBuffer.limit());
        System.out.println("capacity: " + intBuffer.capacity());
        System.out.println("mark: " + intBuffer.mark());

        for (int i = 0; i < 5; i++) {
            System.out.println(intBuffer.get());
        }

        intBuffer.flip();
        System.out.println("flip=========");
        System.out.println("position: " + intBuffer.position());
        System.out.println("limit: " + intBuffer.limit());
        System.out.println("capacity: " + intBuffer.capacity());
        System.out.println("mark: " + intBuffer.mark());
        for (int i = 0; i < 5; i++) {
            System.out.println(intBuffer.get());
        }
        System.out.println(intBuffer.get());
        intBuffer.rewind();
        System.out.println(intBuffer.get());
    }

}
