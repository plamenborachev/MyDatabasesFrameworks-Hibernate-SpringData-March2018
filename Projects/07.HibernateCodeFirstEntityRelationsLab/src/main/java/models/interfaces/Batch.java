package models.interfaces;

import models.shampoo.BasicShampoo;

import java.util.Set;

public interface Batch {

    public int getId();

    public Set<BasicShampoo> getBasicShampoos();
}
