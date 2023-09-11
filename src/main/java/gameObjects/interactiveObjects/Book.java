package gameObjects.interactiveObjects;

import game.state.State;
import gameObjects.entity.Entity;
import gameObjects.graphics.Animation;
import graphics.ImageLoader;

public class Book extends InteractiveObject {

    private String bookTitle;
    private final String[] OPENING_BOOK_IMAGES = {
            "/objects/book/greenBookOpening1.png",
            "/objects/book/greenBookOpening2.png",
            "/objects/book/greenBookOpening3.png",
            "/objects/book/greenBookOpening4.png",
            "/objects/book/greenBookOpening5.png",
            "/objects/book/greenBookOpening6.png"};

    public Book(String bookTitle){
        super( "/objects/book/greenBook.png", 0, 0);
        this.bookTitle = bookTitle;
    }

    @Override
    public String getInteractingMessage() {
        return String.format("Read %s?", bookTitle);
    }

    @Override
    public void interactWith(State state, Entity entity) {
        state.playSound("book");
        this.animation = new Animation(ImageLoader.loadImages(OPENING_BOOK_IMAGES));
    }


}
