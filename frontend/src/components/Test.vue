<template>
	<div class="card">
		<Profil :utilisateur="utilisateur"></Profil>
		<Tweet
			v-for="tweet in utilisateur.tweets"
			:key="tweet.id"
			:tweets="tweet"
		/>
		<div class="form-row">
			<p @click="goToHome()">Timeline</p>
		</div>
		<!-- <span @click="getFollowing()"> {{ nbFollowing }} Abonnements </span>
		<span @click="getFollowers()"> {{ nbFollower }} Abonnés </span> -->
	</div>
</template>
<script>
import { mapState } from "vuex";
import Profil from "@/components/Profil.vue";
import Tweet from "@/components/Tweet.vue";

export default {
	name: "Profile",
	components: {
		Profil,
		Tweet,
	},
	mounted: function () {
		// console.log(this.$store.state.user);
		if (this.$store.state.user.username == "") {
			this.$router.push("/");
			return;
		}

		this.$store.dispatch("getUserInfos");

		console.log(this.$store.state.user.username);
		this.$store.dispatch("getNbFollowers", {
			username: this.$store.state.user.username,
		});
		this.$store.dispatch("getNbFollowing", {
			username: this.$store.state.user.username,
		});
	},
	computed: {
		...mapState({
			utilisateur: "userInfos",
			nbFollower: "nbFollower",
			nbFollowing: "nbFollowing",
		}),
	},
	methods: {
		goToHome: function () {
			this.$router.push("/Home");
		},
		getFollowing: function () {
			// this.$store.dispatch("getFollowing", {
			// 	username: this.utilisateur.username,
			// });
			console.log("Hey");
		},
		getFollowers: function () {
			// this.$store.dispatch("getFollowers", {
			// 	username: this.utilisateur.username,
			// });
			console.log("Hello");
		},
	},
};
</script>

<style>
</style>
