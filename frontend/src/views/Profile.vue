<template>
	<div class="card">
		<h1 class="card_title">Profil</h1>
		<p class="card_subtitle">Hello {{ user.username }} !</p>
		<img :src="user.profilePicture" />
		<div>
			<h2>Tweets</h2>
			<p v-for="(tweet, index) in user.tweets" :key="index">
				{{ tweet.content }} {{ tweet.createdTime }}
			</p>
		</div>
		<div class="form-row">
			<button class="button" @click="logout()">Déconnexion</button>
		</div>
		<div class="form-row">
			<p @click="goToHome()">Timeline</p>
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
		goToHome: function () {
			this.$router.push("/Home");
		},
	},
};
</script>

<style>
</style>
