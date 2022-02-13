package a1.jflores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random ran = new Random();
    private static final FoodItems[] foodItems = FoodItems.values();
    private static final Tools[] tools = Tools.values();
    private static final ToolUses[] toolUses = ToolUses.values();
    private static final Clothes[] clothes = Clothes.values();
    private static final Weapons[] weapons = Weapons.values();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Random ran = new Random();
        List<Item> items = new ArrayList<>();

        //FoodItems[] foodItems = FoodItems.values();
        //Tools[] tools = Tools.values();
        //ToolUses[] toolUses = ToolUses.values();
        //Clothes[] clothes = Clothes.values();
        //Weapons[] weapons = Weapons.values();

        System.out.print("How many items do you want: ");
        int itemCnt = Integer.parseInt(scan.nextLine());

        for (int i = 0; i <itemCnt; i++) {
            int type = ran.nextInt(4);
            switch (type) {
                case 0 -> items.add(genFood());
                case 1 -> items.add(genTool());
                case 2 -> items.add(genClothing());
                case 3 -> items.add(genWeapon());
            }
        }

        //Clothing a = new Clothing("shirt", 10, 1, 5);
        //System.out.println(a);
        for(Item i : items) {
            System.out.println(i);
        }
    }

    public static Food genFood() {
        int foodIndex = ran.nextInt(foodItems.length);
        String foodName = foodItems[foodIndex].toString();
        float foodPrice = ran.nextFloat(10);
        int foodQty = ran.nextInt(30);
        int foodUses = ran.nextInt(6);
        float healthGain = ran.nextFloat(20);
        return new Food(foodName, foodPrice, foodQty, foodUses, healthGain);
        //items.add(tmpFood);
        //return tmpFood;
    }

    public static Tool genTool() {
        int toolIndex = ran.nextInt(tools.length);
        String toolName = tools[toolIndex].toString();
        float toolPrice = ran.nextFloat(200);
        int toolQty = ran.nextInt(15);
        String use = toolUses[toolIndex].toString();
        return new Tool(toolName, toolPrice, toolQty, use);
        //items.add(tmpTool);
        //return tmpTool;
    }

    public static Clothing genClothing() {
        int clothingIndex = ran.nextInt(clothes.length);
        String clothingName = clothes[clothingIndex].toString();
        float clothingPrice = ran.nextFloat(100);
        int clothingQty = ran.nextInt(20);
        float clothingDef = ran.nextFloat(20);
        return new Clothing(clothingName, clothingPrice, clothingQty, clothingDef);
        //items.add(tmpClothing);
        //return tmpClothing;
    }

    public static Weapon genWeapon() {
        int weaponIndex = ran.nextInt(weapons.length);
        String weaponName = weapons[weaponIndex].toString();
        float weaponPrice = ran.nextFloat(100);
        int weaponQty = ran.nextInt(15);
        float weaponAtk = ran.nextFloat(50);
        return new Weapon(weaponName, weaponPrice, weaponQty, weaponAtk);
        //items.add(tmpWeapon);
        //return tmpWeapon;
    }
}

