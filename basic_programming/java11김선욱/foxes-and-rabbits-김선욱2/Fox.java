import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of a fox.
 * Foxes age, move, eat rabbits, and die.
 * 
 * 
 * 여우는 나이먹고, 이동하고, 토끼 잡아먹고, 새끼낳고, 죽는다.
 * 
 * 여우는 매 스텝, 나이를 먹는다.
 * 
 * 여우는 매 스텝, 배고파진다(foodLevel이 1만큼 감소한다). foodLevel이 0 이하로 떨어지면 죽는다.
 * 
 * 여우는 매 스텝, 인접 위치에 토끼가 있으면 잡아 먹고 그리로 이동한다.
 * 
 * 시간이 한 스텝 지나면 반드시 인접 위치로 이동해야 한다.
 * 한 스텝에는 인접한 위치로만 이동할 수 있다.
 * 격자 모장의 필드 위에서, 인접한 위치란 현재 위치(네모)를 둘러싸고 있는 여덟 개의 네모들을 말한다. 
 * 인접 위치인 여덟 개의 네모 모두를 다른 여우들이 차지하고 있으면 (밀도가 너무 높아, 이동할 수 없어) 죽는다.  
 * 
 * 성년이 된 여우만 새끼를 낳을 수 있다.
 * 성년이 된 여우는 어떤 한 스텝에서 새끼를 낳을 수도 있고 낳지 않을 수도 있다.
 * 성년이 된 여우가 어떤 스텝에서 새끼를 낳을 확률 = BREEDING_PROBABILITY.
 * 새끼를 낳으면 아기 여우는 인접한 위치들 중 빈 위치로 가 그 자리를 차지한다.
 * 새끼를 낳는 경우 새끼 수는 아래 방법으로 결정된다.
 *      n = 1 이상 "최대 출산 가능 수" 이하 범위에서 무작위로 결정된 수. 
 *      실제 출산하는 새끼 수 = n과 "인접한 여덟 곳의 네모들 중 비어있는 곳의 수"중 더 작은 수
 * 
 * 
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Fox extends Animal
{
    // Characteristics shared by all foxes (class variables).
    
    // The age at which a fox can start to breed.
    private static final int BREEDING_AGE = 15;  // 성년 (새끼 낳을 수 있는 나이, 스텝 단위)
    // The age to which a fox can live.
    private static final int MAX_AGE = 150; // 수명 (스텝 단위, 150 스텝이 지나면 늙어죽는다)
    // The likelihood of a fox breeding.
    private static final double BREEDING_PROBABILITY = 0.08;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 2;   // 새끼를 최대 몇 마리까지 낳을 수 있나?
    // The food value of a single rabbit. In effect, this is the
    // number of steps a fox can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 9; // 토끼 한 마리 잡아먹으면 9스텝 동안 살 수 있다.
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).

    // The fox's age.
    private int age;
    // Whether the fox is alive or not.
   // private boolean alive;
    // The fox's position.
    //private Location location;
    // The field occupied.
   // private Field field;
    // The fox's food level, which is increased by eating rabbits.
    private int foodLevel;  // 토끼 한 마리 잡아 먹으면 9가 되는데 스텝이 지날 때마다 1씩 감소한다.

    /**
     * Create a fox. A fox can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Fox(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        age = 0;
        
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
        }
        else {
            // leave age at 0
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
        }
    }
    
    /**
     * This is what the fox does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * 
     * 매 스텝마다 Simulator에 의해 이 메소드가 한 번 호출된다.
     * 여우의 일상 생활이 이 메소드로 모델링된다.
     * 
     * @param field The field currently occupied.
     * @param newFoxes A list to return newly born foxes. 새로 태어나는 새끼 여우들을 저장할 컬렉션.
     */
    public void act(List<Animal> newAnimals)
    {
        incrementAge();     // 나이를 한 스텝 먹는다.
        incrementHunger();  // foodLevel이 1만큼 감소한다. foodLevel이 0이하이면 죽는다.
        if(isAlive()) {
            // 새끼를 낳는다. 
            // 새끼들은 newFoxes에 저장된다.       
            // 새끼들은 비어 있는 인접 위치를 차지한다.
            giveBirth(newAnimals);
            // 인접한 위치에 토끼가 있으면 그리로 이동하여 잡아먹는다.
            // 인접한 여러 위치에 토끼들이 있는 경우에는 그 중 아무 것이나 하나만 잡아 먹는다.
            Location newLocation = findFood();
            // 인접한 위치에 토끼가 없어 잡아 먹지 못한 경우,
            // 인접 위치들 중 비어 있는 어느 한 곳으로 이동한다.
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // 인접 위치들 모두에 여우가 있는 경우, 죽는다. Overcrowding.
                setDead();
            }
        }
    }
    
    /**
     * Increase the age. This could result in the fox's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        List<Location> adjacent = getField().adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = getField().getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;    // 인접 위치들에 토끼가 한 마리도 없는 경우.
    }
    
    /**
     * Check whether or not this fox is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newFoxes A list to return newly born foxes.
     */
    private void giveBirth(List<Animal> newAnimals)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = getField().getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Fox young = new Fox(false, getField(), loc);
            newAnimals.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A fox can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
}
