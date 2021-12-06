<template>
	<Navigation />
	<div class="card">
		<Profil v-if="utilisateur" :utilisateur="utilisateur"></Profil>
		<Tweet
			v-for="tweet in utilisateur.tweets"
			:key="tweet.id"
			:tweets="tweet"
		/>
		<follow :utilisateur="utilisateur" />
		<span @click="getFollowing()"> {{ nbFollowing }} Abonnements </span>
		<span @click="getFollowers()"> {{ nbFollower }} Abonnés </span>
	</div>
	<AppFooter />
</template>
<script>
import { mapState } from "vuex";
import Profil from "@/components/Profil.vue";
import Tweet from "@/components/Tweet.vue";
import Follow from "@/components/Follow.vue";
import Navigation from "@/components/Navigation.vue";
import AppFooter from "@/components/AppFooter.vue";

export default {
	name: "Profiles",
	components: {
		Profil,
		Tweet,
		Follow,
		Navigation,
		AppFooter,
	},
	mounted: function () {
		this.$store.dispatch("getAnotherUserInfo", {
			username: this.$route.params.username,
		});
		if (this.$store.state.user.username == "") {
			this.$router.push("/");
			return;
		}

		this.$store.dispatch("getUserInfos");

		this.$store.dispatch("getNbFollowers", {
			username: this.$route.params.username,
		});
		this.$store.dispatch("getNbFollowing", {
			username: this.$route.params.username,
		});
	},
	computed: {
		...mapState({
			utilisateur: "anotherUser",
			nbFollower: "nbFollower",
			nbFollowing: "nbFollowing",
		}),
	},
	methods: {
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
