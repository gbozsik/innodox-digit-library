
A frontend az src\main\resources\static mappába be van build-elve.
a http://http://localhost:9000-en érhető el

Felhasználók:					jelszó:

sanyi.kovacs@gmail.com, 		pas
feri.sos@t-online.com,			pas
tamas.kis@gmail.com				pas

A Spring Boot spring security-t, basicAuthentication-t használ
Az endpoint hívások interface-eken keresztül érik el a serviceket.
H2 adatbázist használ, amit a DatabaseLoader osztály tölt fel induláskor




A frontend: src\main\resources\static mappában van build-elve.
			
			de a https://github.com/gbozsik/innodox-front.git linken van amiből a build készült
		
		-Vue.js keretrendszert használ és az abba épülő vuetify-t
		
		-Wrappelt komponensek vannak  components/core mappában, miket a View-ek implementálnak
		-store.js ami async metódusokkal (actions) kommunikál a backendel, és sync (mutation) metódusokkal tárolja a
		 kapott adatokat
		-App.vue a fő View a toolbart, menüt tartalmazza
		-A View-ben a 
		   	-mounted() betöltéskor fut meg, a 
			-computed() figyeli a a store.js-ben a state-ben tárolt
			 állapot változók tartalmát
		-main.js-ben vannak a importálva és konfigurálva a komponensek
			-Axios végzi a backendel a kommunikációt json-al
		-router.js a View-ek eléréseit határozza meg	
		-vue.config.js config fájlban van megadva a fejlesztés alatt külön használt 4000-es port amin elérhető és 
		 proxy-n keresztül érte el a backend-et
			
			
			
