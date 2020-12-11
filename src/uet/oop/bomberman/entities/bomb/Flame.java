package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.graphics.Screen;

public class Flame extends Entity {

	protected Board _board;
	protected int huong_;
	private int do_dai_;
	protected int toa_do_x, toa_do_y;
	protected FlameSegment[] _flameSegments = new FlameSegment[0];
	
	public Flame(int x, int y, int huong, int do_dai, Board board) {
		toa_do_x = x;
		toa_do_y = y;
		_x = x;
		_y = y;
		huong_ = huong;
		do_dai_ = do_dai;
		_board = board;
		createFlameSegments();
	}

	private void createFlameSegments() {
		_flameSegments = new FlameSegment[calculatePermitedDistance()];

		boolean last = false;

		int x = (int)_x;
		int y = (int)_y;
		for (int i = 0; i < _flameSegments.length; i++) {
			last = i == _flameSegments.length -1 ? true : false;

			switch (huong_) {
				case 0: y--; break;
				case 1: x++; break;
				case 2: y++; break;
				case 3: x--; break;
			}
			_flameSegments[i] = new FlameSegment(x, y, huong_, last);
		}
	}

	private int calculatePermitedDistance() {
		int do_dai = 0;
		int x = (int)_x;
		int y = (int)_y;
		while(do_dai < do_dai_) {
			if(huong_ == 0) y--;
			if(huong_ == 1) x++;
			if(huong_ == 2) y++;
			if(huong_ == 3) x--;

			Entity a = _board.getEntity(x, y, null);

			if(a instanceof Character) ++do_dai;

			if(a.collide(this) == false)
				break;

			++do_dai;
		}
		return do_dai;
	}
	
	public FlameSegment flameSegmentAt(int x, int y) {
		for (int i = 0; i < _flameSegments.length; i++) {
			if(_flameSegments[i].getX() == x && _flameSegments[i].getY() == y)
				return _flameSegments[i];
		}
		return null;
	}

	@Override
	public void update() {}
	
	@Override
	public void render(Screen screen) {
		for (int i = 0; i < _flameSegments.length; i++) {
			_flameSegments[i].render(screen);
		}
	}

	@Override
	public boolean collide(Entity e) {
		if (e instanceof Bomber) {
			((Bomber) e).kill();
			return false;
		}

		if (e instanceof Enemy) {
			((Enemy) e).kill();
			return false;
		}

		return true;
	}
}
