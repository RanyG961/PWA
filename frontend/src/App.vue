<template>

	<router-view></router-view>
</template>

<script>

export default {
	name: "App",
	
};
</script>

<style>
@font-face {
  font-family: gotFont;
  src: url(./assets/GOT.ttf);
}

* {
	font-family: cascadia-code, sans-serif;
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

#app {
	max-width: 100%;
}

body {
	background-image: linear-gradient(62deg, #4e7bbe 0%, #a2bfe0 100%);
	min-height: 100vh;
}

header{
	display: flex;
	height: 7vh;
	width: 100%;
	background-image: linear-gradient(62deg, #b2bac9 0%, #dfe6ee 100%);
}

.bouton_toggle{
	margin-left: 2em;
	align-self: center;
}

.redirection{
	margin-left: auto;
    margin-right: auto;
	align-self: center;
}

.lien{
	padding: 2em;
}

img {
	max-width: 100%;
	border-radius: 8px;
}

.button {
	background: #2196f3;
	color: white;
	border-radius: 8px;
	font-weight: 800;
	font-size: 15px;
	border: none;
	width: 100%;
	padding: 16px;
	transition: 0.4s background-color;
}

.button:hover {
	cursor: pointer;
	background: #1976d2;
}

.button--disabled {
	background: #cecece;
	color: #ececec;
}
.button--disabled:hover {
	cursor: not-allowed;
	background: #cecece;
}
</style>
