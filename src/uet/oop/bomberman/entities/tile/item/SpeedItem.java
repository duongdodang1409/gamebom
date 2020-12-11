package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
	protected boolean _active;

	public SpeedItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		_active = false;
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
		if(e instanceof Bomber) {

			if (!_active) {
				_active = true;
				Game.addBomberSpeed(0.1);
			}
			remove();
			return true;
		}
		return false;
	}
}
