package com.polypenguin.crayon.engine.utils.miscellaneous;

import org.bukkit.inventory.Inventory;

public class MaterialSet {
    private Inventory stone;
    private Inventory natural;
    private Inventory wood;
    private Inventory slab;
    private Inventory cOne;
    private Inventory cTwo;
    private Inventory cThree;
    private Inventory sea;
    private Inventory random;

    public MaterialSet(Inventory stone, Inventory natural, Inventory wood, Inventory slab, Inventory cOne, Inventory cTwo, Inventory cThree, Inventory sea, Inventory random) {
        this.stone = stone;
        this.natural = natural;
        this.wood = wood;
        this.slab = slab;
        this.cOne = cOne;
        this.cTwo = cTwo;
        this.cThree = cThree;
        this.sea = sea;
        this.random = random;
    }

    public Inventory getStone() {
        return this.stone;
    }

    public Inventory getNatural() {
        return this.natural;
    }

    public Inventory getWood() {
        return this.wood;
    }

    public Inventory getSlab() {
        return this.slab;
    }

    public Inventory getcOne() {
        return this.cOne;
    }

    public Inventory getcTwo() {
        return this.cTwo;
    }

    public Inventory getcThree() {
        return this.cThree;
    }

    public Inventory getSea() {
        return this.sea;
    }

    public Inventory getRandom() {
        return this.random;
    }
}