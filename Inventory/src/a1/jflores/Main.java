package a1.jflores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();
        List<Item> items = new ArrayList<>();

        FoodItems[] foodItems = FoodItems.values();
        Tools[] tools = Tools.values();
        ToolUses[] toolUses = ToolUses.values();
        Clothes[] clothes = Clothes.values();
        Weapons[] weapons = Weapons.values();

        System.out.print("How many items do you want: ");
        int itemCnt = Integer.parseInt(scan.nextLine());

        for (int i = 0; i <itemCnt; i++) {
            int type = ran.nextInt(4);
            switch (type) {
                case 0 -> {
                    int foodIndex = ran.nextInt(foodItems.length);
                    String foodName = foodItems[foodIndex].toString();
                    float foodPrice = ran.nextFloat(10);
                    int foodQty = ran.nextInt(30);
                    int foodUses = ran.nextInt(6);
                    float healthGain = ran.nextFloat(20);
                    Food tmpFood = new Food(foodName, foodPrice, foodQty, foodUses, healthGain);
                    items.add(tmpFood);
                }
                case 1 -> {
                    int toolIndex = ran.nextInt(tools.length);
                    String toolName = tools[toolIndex].toString();
                    float toolPrice = ran.nextFloat(200);
                    int toolQty = ran.nextInt(15);
                    String use = toolUses[toolIndex].toString();
                    Tool tmpTool = new Tool(toolName, toolPrice, toolQty, use);
                    items.add(tmpTool);
                }
                case 2 -> {
                    int clothingIndex = ran.nextInt(clothes.length);
                    String clothingName = clothes[clothingIndex].toString();
                    float clothingPrice = ran.nextFloat(100);
                    int clothingQty = ran.nextInt(20);
                    float clothingDef = ran.nextFloat(20);
                    Clothing tmpClothing = new Clothing(clothingName, clothingPrice, clothingQty, clothingDef);
                    items.add(tmpClothing);
                }
                case 3 -> {
                    int weaponIndex = ran.nextInt(weapons.length);
                    String weaponName = weapons[weaponIndex].toString();
                    float weaponPrice = ran.nextFloat(100);
                    int weaponQty = ran.nextInt(15);
                    float weaponAtk = ran.nextFloat(50);
                    Weapon tmpWeapon = new Weapon(weaponName, weaponPrice, weaponQty, weaponAtk);
                    items.add(tmpWeapon);
                }
            }
        }

        //Clothing a = new Clothing("shirt", 10, 1, 5);
        //System.out.println(a);
        for(Item i : items) {
            System.out.println(i);
        }
    }
}

