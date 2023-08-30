package org.blbulyandavbulyan;

/**
 * Класс предоставляющий игрушку
 */
public class Toy {
    private final Long id;
    private final String title;
    private long amount;
    private double dropFrequency;

    /**
     * Создаёт экземпляр игрушки
     * @param id идентификатор игрушки
     * @param title название
     * @param amount количество
     * @param dropFrequency частота выпадения
     */
    public Toy(Long id, String title, long amount, double dropFrequency) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.dropFrequency = dropFrequency;
    }
    /**
     * @return ID переданный в конструкторе
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return название переданное в конструкторе
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return текущее количество
     */
    public long getAmount() {
        return amount;
    }

    /**
     * @return получает текущую частоту выпадения
     */
    public double getDropFrequency() {
        return dropFrequency;
    }

    /**
     * Устанавливает частоту выпадения
     * @param dropFrequency новая частота выпадения
     */
    public void setDropFrequency(double dropFrequency) {
        this.dropFrequency = dropFrequency;
    }
    public void decreaseAmount(){
        amount--;
    }
}
