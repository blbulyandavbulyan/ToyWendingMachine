package org.blbulyandavbulyan;

import java.util.*;

/**
 * Предоставляет автомат с игрушками
 */
public class ToyVendingMachine {
    private final Map<Long, Toy> idToToy = new HashMap<>();
    private final HashMap<Long, Double> idToToProbability = new HashMap<>();
    private long nextId = 1L;
    private void recalculateProbabilities(){
        double frequencySum = idToToy.values().stream().mapToDouble(Toy::getDropFrequency).sum();
        for(Toy toy : idToToy.values()){
            idToToProbability.put(toy.getId(), toy.getDropFrequency()/frequencySum);
        }
    }

    /**
     * Метод добавляет игрушку в автомат
     * @param title название игрушки
     * @param amount количество
     * @param dropFrequency частота выпадения
     * @return ID новой игрушки
     * @throws IllegalArgumentException если dropFrequency <= 0 или amount <= 0
     */
    public long addToy(String title, Long amount, double dropFrequency){
        if(amount <= 0)throw new IllegalArgumentException();
        if(dropFrequency <=0 )throw new IllegalArgumentException();
        var toy = new Toy(nextId++, title, amount, dropFrequency);
        idToToy.put(toy.getId(), toy);
        recalculateProbabilities();
        return toy.getId();
    }

    /**
     * Получает случайную игрушку в соответствии с частотой выпадения
     * @return случайная игрушка
     * @throws NoSuchElementException если нет игрушек в автомате
     */
    public ToyForUser getRandomToy(){
        double random = Math.random();
        Comparator<Toy> toyComparator = Comparator.comparingDouble(Toy::getDropFrequency);
        Toy result = idToToy.values().stream()
                .filter((toy) -> Double.compare(toy.getDropFrequency(), random) <= 0)
                .max(toyComparator)
                .or(()->idToToy.values().stream().max(toyComparator)).orElseThrow();
        idToToProbability.remove(result.getId());
        if(result.getAmount() == 1) {
            idToToy.remove(result.getId());
            recalculateProbabilities();
        }
        else result.decreaseAmount();
        return new ToyForUser(result.getId(), result.getTitle(), result.getDropFrequency());
    }

    /**
     * Устанавливает новую частоту выпадения игрушки
     * @param id идентификатор игрушки
     * @param dropFrequency новая частота выпадения
     * @throws IllegalArgumentException если частота выпадения <= 0
     * @throws NoSuchElementException если нет в автомате игрушки с таким id
     */
    public void setDropFrequency(long id, double dropFrequency){
        Toy toy = idToToy.get(id);
        if(toy == null)
            throw new NoSuchElementException();
        if(dropFrequency <= 0)throw new IllegalArgumentException();
        if(Double.compare(dropFrequency, toy.getDropFrequency()) != 0){
            toy.setDropFrequency(dropFrequency);
            recalculateProbabilities();
        }
    }
}
