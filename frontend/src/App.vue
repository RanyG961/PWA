<template>
	<router-view></router-view>
</template>

<script>
export default {
	name: "App",
	components: {},
};
</script>

<style>
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
	background-image: linear-gradient(62deg, #fbab7e 0%, #f7ce68 100%);
	display: flex;
	align-items: center;
	justify-content: center;
	min-height: 100vh;
	padding: 32px;
}

img {
	max-width: 100%;
	border-radius: 8px;
}

.card {
	max-width: 100%;
	width: 540px;
	background: white;
	border-radius: 16px;
	padding: 32px;
}

.card_title {
	text-align: center;
	font-weight: 800;
}

.card_subtitle {
	text-align: center;
	color: #666;
	font-weight: 500;
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

.card_action {
	color: #2196f3;
	text-decoration: underline;
}

.card_action:hover {
	cursor: pointer;
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
