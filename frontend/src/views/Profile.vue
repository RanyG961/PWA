<template>
	<div class="card">
		<h1 class="card_title">Espace Perso</h1>
		<p class="card_subtitle">Hello {{ user.username }} !</p>
		<img :src="user.profilePicture" />
		<div class="form-row">
			<button class="button" @click="logout()">Déconnexion</button>
		</div>
	</div>
</template>

<script>
import { mapState } from "vuex";
export default {
	name: "Profile",
	mounted: function () {
		// console.log(this.$store.state.user);
		if (this.$store.state.user.username == "") {
			this.$router.push("/");
			return;
		}

		this.$store.dispatch("getUserInfos");
	},
	computed: {
		...mapState({
			user: "userInfos",
		}),
	},
	methods: {
		logout: function () {
			this.$store.commit("logout");
			this.$router.push("/");
		},
	},
};
</script>

<style>
</style>
