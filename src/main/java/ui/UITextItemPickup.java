package ui;

import game.Game;
import game.state.GameState;
import gameObjects.entity.npc.NPC;
import gameObjects.interactiveObjects.InteractiveObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;

public class UITextItemPickup extends UIText {

    public UITextItemPickup() {
        InputStream inputStream = getClass().getResourceAsStream("/fonts/Palette-regular.otf");

        try {
            palette = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            palette = palette.deriveFont(Font.PLAIN, 32F);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = (Game.SCREEN_WIDTH / 2);
        y = (Game.SCREEN_HEIGHT / 2) - Game.TILE_SIZE / 2;
    }

    public void draw(Graphics2D g2, GameState state){
        NPC nearbyNPC = state.getPlayer().getNearbyNPC();
        String message = null;
        if(nearbyNPC != null){
            message = "Talk to ".concat(nearbyNPC.getName());
        } else {
            InteractiveObject interactiveObject = state.getPlayer().getInteractiveObject();
            if(interactiveObject != null) {
                message = interactiveObject.getInteractingMessage();
            }
        }


            if(message != null){
                g2.setFont(palette);
                g2.setColor(Color.WHITE);
                Rectangle2D r = palette.getStringBounds(message, g2.getFontRenderContext());
                g2.drawString(message, (int) (x - r.getWidth() / 2) + Game.TILE_SIZE / 2, y);
            }

    }
}
