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
		<Deconnexion />
	</div>
</template>
<script>
import { mapState } from "vuex";
import Profil from "@/components/Profil.vue";
import Tweet from "@/components/Tweet.vue";
import Deconnexion from "@/components/Deconnexion.vue";

export default {
	name: "Profile",
	components: {
		Profil,
		Tweet,
		Deconnexion,
	},
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
			utilisateur: "userInfos",
		}),
	},
	methods: {
		goToHome: function () {
			this.$router.push("/Home");
		},
	},
};
</script>

<style>
</style>
