package programmer_interview;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 猫狗队列
 * created by Ethan-Walker on 2018/12/16
 */
public class Q004_PetQueue {

    ArrayDeque<PetEnterSeq> dogsQueue;
    ArrayDeque<PetEnterSeq> catsQueue;

    long count;  // 计数，标识这是第 xx 个进入宠物队列

    public Q004_PetQueue() {
        this.dogsQueue = new ArrayDeque<>();
        this.catsQueue = new ArrayDeque<>();
        count = 0;
    }

    public void add(Pet pet) {
        if (pet.getClass() == Dog.class) {
            dogsQueue.offer(new PetEnterSeq(pet, count++));
        } else if (pet.getClass() == Cat.class) {
            catsQueue.offer(new PetEnterSeq(pet, count++));
        } else {
            throw new RuntimeException("can't identify kind of the pet!");
        }
    }

    public ArrayList<Pet> pollAll() {
        ArrayList<Pet> list = new ArrayList<>();
        if (isEmpty()) {
            return list;
        }
        PetEnterSeq dog, cat;
        while (!dogsQueue.isEmpty() && !catsQueue.isEmpty()) {
            dog = dogsQueue.peek();
            cat = catsQueue.peek();
            if (dog.getCount() < cat.getCount()) {
                list.add(dog.getPet());
                dogsQueue.poll();
            } else {
                list.add(cat.getPet());
                catsQueue.poll();
            }
        }
        while (!dogsQueue.isEmpty()) {
            dog = dogsQueue.peek();
            list.add(dog.getPet());
            dogsQueue.poll();
        }
        while (!catsQueue.isEmpty()) {
            cat = catsQueue.peek();
            list.add(cat.getPet());
            catsQueue.poll();
        }

        return list;
    }

    public boolean isEmpty() {
        return dogsQueue.isEmpty() && catsQueue.isEmpty();
    }

    public Pet pollDog() {
        if (dogsQueue.isEmpty()) {
            throw new RuntimeException("dogs queue is empty!");
        }
        return dogsQueue.poll().getPet();
    }

    public Pet pollCat() {
        if (catsQueue.isEmpty()) {
            throw new RuntimeException("cats queue is empty!");
        }
        return catsQueue.poll().getPet();
    }

    public boolean isDogEmpty() {
        return this.dogsQueue.isEmpty();
    }

    public boolean isCatEmpty() {
        return this.catsQueue.isEmpty();
    }
}

class PetEnterSeq {
    private Pet pet;
    private long count;

    public PetEnterSeq(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    public Pet getPet() {
        return pet;
    }
}

class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;

    }

    @Override
    public String toString() {
        return this.type;
    }
}

class Dog extends Pet {
    public Dog() {
        super("dog");
    }
}

class Cat extends Pet {
    public Cat() {
        super("cat");
    }
}
