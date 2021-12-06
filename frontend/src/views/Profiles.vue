<template>
	<Navigation />
	<div class="card">
		<Profil :utilisateur="utilisateur"></Profil>
		<Tweet
			v-for="tweet in utilisateur.tweets"
			:key="tweet.id"
			:tweets="tweet"
		/>
		<follow :utilisateur="utilisateur" />
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
	},
	computed: {
		...mapState({
			utilisateur: "anotherUser",
		}),
	},
};
</script>

<style>
</style>
