package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

	Texture[] imgKomar = new Texture[11]; // ссылки на изображения
	Texture imgBackGround; // фоновое изображение
	Texture imgBtnExit;
	Texture imgBtnSndOn, imgBtnSndOff;
	Texture imgBtnPause, imgBtnPlay;

	Sound[] sndKomar = new Sound[4];
	// Music sndMusic;

	public static float scrWidth = 1280;
	public static float scrHeight = 720;

	// кнопки интерфейса игры
	MosButton btnExit;

	// создаём массив ссылок на объекты комаров
	Mosquito[] komar = new Mosquito[10];
	int kills = 0;

	// переменные для работы с таймером
	long timeStartGame, timeCurrently;

	@Override
	public void create () {
		batch = new SpriteBatch(); // создать объект, отвечающий за вывод изображений
		camera = new OrthographicCamera();
		camera.setToOrtho(false, scrWidth, scrHeight);
		touch = new Vector3();

		createFont();

		// загружаем картинки
		for(int i=0; i<imgKomar.length; i++) {
			imgKomar[i] = new Texture("mosq"+i+".png"); // создать объект-картинку и загрузить в него изображение
		}
		imgBackGround = new Texture("moscowcity.jpg");
		imgBtnExit = new Texture("exit.png");

		// загружаем звуки
		for(int i=0; i<sndKomar.length; i++) {
			sndKomar[i] = Gdx.audio.newSound(Gdx.files.internal("mos"+i+".mp3"));
		}
		//sndMusic = Gdx.audio.newMusic(Gdx.files.internal("jinglebells.mp3"));
		//sndMusic.setLooping(true);
		//sndMusic.play();

		// создаём кнопки
		btnExit = new MosButton(scrWidth-60, scrHeight-60, 50);

		// создаём объекты комаров
		for(int i=0; i<komar.length; i++){
			komar[i] = new Mosquito();
		}

		// узнаём время старта игры
		timeStartGame = TimeUtils.millis();
	}

	void createFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("wellwait.otf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
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

	@Override
	public void render () {
		// касания экрана
		if(Gdx.input.justTouched()){
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			for(int i=komar.length-1; i>=0; i--) {
				if(komar[i].isAlive && komar[i].hit(touch.x, touch.y)) {
					kills++;
					sndKomar[MathUtils.random(0, 3)].play();
					break;
				}
			}
			if(btnExit.hit(touch.x, touch.y)){
				Gdx.app.exit();
			}
		}

		// события игры
		for(int i=0; i<komar.length; i++) {
			komar[i].fly();
		}
		timeCurrently = TimeUtils.millis() - timeStartGame;

		// вывод изображений
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(imgBackGround, 0, 0, scrWidth, scrHeight);
		for(int i=0; i<komar.length; i++) {
			batch.draw(imgKomar[komar[i].faza], komar[i].x, komar[i].y, komar[i].width, komar[i].height, 0, 0, 500, 500, komar[i].isFlip(), false);
		}
		batch.draw(imgBtnExit, btnExit.x, btnExit.y, btnExit.width, btnExit.height);
		font.draw(batch, "KILLS: "+kills, 10, scrHeight-10);
		font.draw(batch, "TIME: "+timeToString(timeCurrently), scrWidth-450, scrHeight-10);
		batch.end();
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
	}
}
