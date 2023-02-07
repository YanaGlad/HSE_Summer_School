package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch; // ссылка на объект, отвечающий за вывод изображений
	OrthographicCamera camera;
	Vector3 touch;
	BitmapFont font;
	InputKeyboard keyboard;

	Texture[] imgKomar = new Texture[11]; // ссылки на изображения
	Texture imgBackGround; // фоновое изображение
	Texture imgBtnExit;
	Texture imgBtnSndOn, imgBtnSndOff;
	Texture imgBtnPause, imgBtnPlay;

	Sound[] sndKomar = new Sound[4];
	// Music sndMusic;

	// ширина и высота экрана
	public static final float SCR_WIDTH = 1280;
	public static final float SCR_HEIGHT = 720;

	// кнопки интерфейса игры
	MosButton btnExit;
	MosButton btnSound;

	// создаём массив ссылок на объекты комаров
	Mosquito[] komar = new Mosquito[5];
	int kills;

	// переменные для работы с таймером
	long timeStartGame, timeCurrently;

	// логические переменные
	boolean soundOn = true;

	// состояния игры
	public static final int PLAY_GAME = 1, ENTER_NAME = 2, SHOW_TABLE = 3;
	int situation;

	String name;
	long time;

	// таблица рекордов
	Player[] players = new Player[7];

	@Override
	public void create () {
		batch = new SpriteBatch(); // создать объект, отвечающий за вывод изображений
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touch = new Vector3();

		createFont();

		keyboard = new InputKeyboard(SCR_WIDTH, SCR_HEIGHT, 10);

		// загружаем картинки
		for(int i=0; i<imgKomar.length; i++) {
			imgKomar[i] = new Texture("mosq"+i+".png"); // создать объект-картинку и загрузить в него изображение
		}
		imgBackGround = new Texture("moscowcity.jpg");
		imgBtnExit = new Texture("exit.png");
		imgBtnSndOn = new Texture("sndon.png");
		imgBtnSndOff = new Texture("sndoff.png");

		// загружаем звуки
		for(int i=0; i<sndKomar.length; i++) {
			sndKomar[i] = Gdx.audio.newSound(Gdx.files.internal("mos"+i+".mp3"));
		}
		//sndMusic = Gdx.audio.newMusic(Gdx.files.internal("jinglebells.mp3"));
		//sndMusic.setLooping(true);
		//sndMusic.play();

		// создаём кнопки
		btnExit = new MosButton(SCR_WIDTH -60, SCR_HEIGHT -60, 50);
		btnSound = new MosButton(SCR_WIDTH -60, SCR_HEIGHT -120, 50);

		for (int i = 0; i < players.length; i++) {
			players[i] = new Player("noname", 0);
		}
		loadTableOfRecords();
		gameStart();
	}

	@Override
	public void render () {
		// касания экрана
		if(Gdx.input.justTouched()){
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			if(situation == PLAY_GAME) {
				for (int i = komar.length - 1; i >= 0; i--) {
					if (komar[i].isAlive && komar[i].hit(touch.x, touch.y)) {
						kills++;
						if (soundOn) {
							sndKomar[MathUtils.random(0, 3)].play();
						}
						if (kills == komar.length) {
							situation = ENTER_NAME;
						}
						break;
					}
				}
			}
			if(situation == SHOW_TABLE){
				gameStart();
			}
			if(situation == ENTER_NAME){
				keyboard.hit(touch.x, touch.y);
				if(keyboard.endOfEdit()){
					situation = SHOW_TABLE;
					players[players.length-1].name = keyboard.getText();
					players[players.length-1].time = timeCurrently;
					sortTableOfRecords();
					saveTableOfRecords();
				}
			}

			// нажатия на экранные кнопки
			if(btnExit.hit(touch.x, touch.y)){
				Gdx.app.exit(); // выход из игры
			}
			if(btnSound.hit(touch.x, touch.y)){
				soundOn = !soundOn;
			}
		}

		// события игры
		for(int i=0; i<komar.length; i++) {
			komar[i].fly();
		}
		if(situation == PLAY_GAME) {
			timeCurrently = TimeUtils.millis() - timeStartGame;
		}

		// вывод изображений
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
		for(int i=0; i<komar.length; i++) {
			batch.draw(imgKomar[komar[i].faza], komar[i].x, komar[i].y, komar[i].width, komar[i].height, 0, 0, 500, 500, komar[i].isFlip(), false);
		}
		batch.draw(imgBtnExit, btnExit.x, btnExit.y, btnExit.width, btnExit.height);
		if(soundOn) {
			batch.draw(imgBtnSndOn, btnSound.x, btnSound.y, btnSound.width, btnSound.height);
		} else {
			batch.draw(imgBtnSndOff, btnSound.x, btnSound.y, btnSound.width, btnSound.height);
		}
		font.draw(batch, "KILLS: "+kills, 10, SCR_HEIGHT -10);
		font.draw(batch, "TIME: "+timeToString(timeCurrently), SCR_WIDTH -450, SCR_HEIGHT -10);
		if(situation == ENTER_NAME){
			keyboard.draw(batch);
		}
		if(situation == SHOW_TABLE){
			for (int i = 0; i < players.length; i++) {
				font.draw(batch, players[i].name+"...."+timeToString(players[i].time), SCR_WIDTH/3, SCR_HEIGHT*3/4-i*50);
			}
		}
		batch.end();
	}
	void createFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("wellwait.otf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.characters = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
		parameter.size = 50;
		parameter.color = Color.ORANGE;
		parameter.borderWidth = 3;
		parameter.borderColor = Color.BLACK;
		font = generator.generateFont(parameter);
	}

	String timeToString(long t){
		String sec = "" + t/1000%60/10 + t/1000%60%10;
		String min = "" + t/1000/60/10 + t/1000/60%10;
		return min+":"+sec;
	}

	void gameStart() {
		situation = PLAY_GAME;
		kills = 0;
		// создаём объекты комаров
		for(int i=0; i<komar.length; i++){
			komar[i] = new Mosquito();
		}

		// узнаём время старта игры
		timeStartGame = TimeUtils.millis();
	}

	void gameOver(){

	}

	void sortTableOfRecords(){

		for (int i = 0; i < players.length; i++) {
			if(players[i].time == 0) players[i].time = 1000000000;
		}
		for (int j = 0; j < players.length; j++) {
			for (int i = 0; i < players.length - 1; i++) {
				if (players[i].time > players[i + 1].time) {
					long z = players[i].time;
					players[i].time = players[i + 1].time;
					players[i + 1].time = z;
					String s = players[i].name;
					players[i].name = players[i + 1].name;
					players[i + 1].name = s;
				}
			}
		}
		for (int i = 0; i < players.length; i++) {
			if(players[i].time == 1000000000) players[i].time = 0;
		}
	}

	void saveTableOfRecords() {
		Preferences preferences = Gdx.app.getPreferences("TaleOfRecords");
		for (int i = 0; i < players.length; i++) {
			preferences.putString("name"+i, players[i].name);
			preferences.putLong("time"+i, players[i].time);
		}
		preferences.flush();
	}

	void loadTableOfRecords() {
		Preferences preferences = Gdx.app.getPreferences("TaleOfRecords");
		for (int i = 0; i < players.length; i++) {
			if(preferences.contains("name"+i)) players[i].name = preferences.getString("name"+i, "none");
			if(preferences.contains("time"+i)) players[i].time = preferences.getLong("time"+i, 0);
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		for (int i = 0; i < imgKomar.length; i++) {
			imgKomar[i].dispose();
		}
		imgBackGround.dispose();
		imgBtnExit.dispose();
		//sndMusic.dispose();
		keyboard.dispose();
	}
}
