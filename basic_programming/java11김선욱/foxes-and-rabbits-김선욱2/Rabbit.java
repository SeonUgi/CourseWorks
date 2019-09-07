import java.util.List;
import java.util.Random;

/**
 * A simple model of a rabbit.
 * Rabbits age, move, breed, and die.
 * 
 * 토끼는 나이먹고, 이동하고, 새끼낳고, 죽는다.
 * 
 * 토끼는 매 스텝, 나이를 먹는다.
 * 
 * 시간이 한 스텝 지나면 반드시 인접 위치로 이동해야 한다.
 * 한 스텝에는 인접한 위치로만 이동할 수 있다.
 * 격자 모장의 필드 위에서, 인접한 위치란 현재 위치(네모)를 둘러싸고 있는 여덟 개의 네모들을 말한다. 
 * 인접 위치인 여덟 개의 네모 모두를 다른 동물이 차지하고 있으면 (밀도가 너무 높아, 이동할 수 없어) 죽는다.  
 * 
 * 성년이 된 토끼만 새끼를 낳을 수 있다.
 * 성년이 된 토끼는 어떤 한 스텝에서 새끼를 낳을 수도 있고 낳지 않을 수도 있다.
 * 성년이 된 토끼가 어떤 스텝에서 새끼를 낳을 확률 = BREEDING_PROBABILITY.
 * 새끼를 낳으면 아기 토끼는 인접한 위치들 중 빈 위치로 가 그 자리를 차지한다.
 * 새끼를 낳는 경우 새끼 수는 아래 방법으로 결정된다.
 *      n = 1 이상 "최대 출산 가능 수" 이하 범위에서 무작위로 결정된 수. 
 *      실제 출산하는 새끼 수 = n과 "인접한 여덟 곳의 네모들 중 비어있는 곳의 수"중 더 작은 수
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Rabbit extends Animal
{
    // Characteristics shared by all rabbits (class variables).

    // The age at which a rabbit can start to breed.
    private static final int BREEDING_AGE = 5;  // 성년 (새끼 낳을 수 있는 나이, 스텝 단위)
    // The age to which a rabbit can live.
    private static final int MAX_AGE = 40;  // 수명 (스텝 단위, 40스텝이 지나면 늙어죽는다)
    // The likelihood of a rabbit breeding.
    private static final double BREEDING_PROBABILITY = 0.12;    // 한 스텝에서 새끼를 낳을 확률
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;   // 새끼를 최대 몇 마리까지 낳을 수 있나?
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // The rabbit's age.
    private int age;

    /**
     * Create a new rabbit. A rabbit may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the rabbit will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Rabbit(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
    /**
     * This is what the rabbit does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * 매 스텝마다 Simulator에 의해 이 메소드가 한 번 호출된다.
     * 토끼의 일상 생활이 이 메소드로 모델링된다.
     * @param newRabbits 새로 태어나는 새끼 토끼들을 저장할 컬렉션.
     */
    public void act(List<Animal> newAnimals)
    {
        incrementAge();                 // 나이를 한 스텝 먹는다.
        if(isAlive()) {
            // 새끼를 낳는다. 
            // 새끼들은 newRabbits에 저장된다.       
            // 새끼들은 비어 있는 인접 위치를 차지한다.
            giveBirth(newAnimals);     
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Increase the age.
     * This could result in the rabbit's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Check whether or not this rabbit is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newRabbits A list to return newly born rabbits.
     */
    private void giveBirth(List<Animal> newAnimals)
    {
        // New rabbits are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = getField().getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Rabbit young = new Rabbit(false, getField(), loc);
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
     * A rabbit can breed if it has reached the breeding age.
     * @return true if the rabbit can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
}
