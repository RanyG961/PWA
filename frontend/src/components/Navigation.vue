<template>
	<div v-if="!afficher" class="">
		<button id="open" class="" @click="toggle_open()">&#9776;</button>
	</div>
	<div class="sidebar" v-if="afficher">
		<div class="menu">
			<button id="close" @click="toggle_close()">Close &times;</button>
		</div>
		<a>
			<router-link to="/home" class="lien">
				<p @click="toggle_close()">Home</p>
			</router-link>
		</a>
		<a>
			<router-link to="/profile" class="lien">
				<p @click="toggle_close()">Profile</p>
			</router-link>
		</a>
		<Deconnexion />
	</div>
</template>

<script>
import Deconnexion from "@/components/Deconnexion.vue";
export default {
	name: "navigation",
	data: function () {
		return {
			afficher: false,
		};
	},
	components: {
		Deconnexion,
	},
	methods: {
		toggle_open() {
			this.afficher = !this.afficher;
			document.querySelector(".main_body").style.marginLeft = "25%";
			document.querySelector(".sidebar").style.width = "25%";
			document.querySelector(".sidebar").style.display = "block";
		},
		toggle_close() {
			this.afficher = !this.afficher;
			document.querySelector(".main_body").style.marginLeft = "0%";
			document.querySelector(".sidebar").style.display = "none";
		},
	},
};
</script>

<style>
.bouton_toggle {
	margin-left: 2em;
	align-self: center;
}

.redirection {
	margin-left: auto;
	margin-right: auto;
	align-self: center;
}

.sidebar {
	color: white;
	background-color: rgb(9, 90, 165);
	float: left;
	position: fixed;
	z-index: 1;
	top: 0;
	left: 0;
	bottom: 0;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	font-family: gotFont;
	font-size: 2rem;
	width: 250px;
}

.sidebar a {
	margin-top: 20%;
}

#open {
	height: 5em;
	width: 5em;
	background-color: rgb(143, 143, 180);
}

#close {
	height: 5em;
	width: 5em;
	background-color: rgb(143, 143, 180);
}
</style>

