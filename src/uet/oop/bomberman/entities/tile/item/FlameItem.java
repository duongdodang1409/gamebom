package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {
	protected boolean _active;

	public FlameItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		_active = false;
	}

	@Override
	public boolean collide(Entity e) {
		if(e instanceof Bomber) {

			if (!_active) {
				_active = true;
				Game.addBombRadius(1);
			}
			remove();
			return true;
		}
		return false;
	}

}
