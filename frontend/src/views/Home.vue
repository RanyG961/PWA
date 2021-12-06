<template>
	<div>
		<Navigation />
		<div class="timeline">
			<h1>Timeline</h1>
			<addTweet />
			<Tweet v-for="tweet in tweets" :key="tweet.id" :tweets="tweet" />
			<ListUsers :users="users" />
			<Deconnexion />
			<AppFooter />
		</div>
	</div>
</template>

<script>
import Tweet from "@/components/Tweet.vue";
import addTweet from "@/components/addTweet.vue";
import ListUsers from "@/components/ListUsers.vue";
import Deconnexion from "@/components/Deconnexion.vue";
import Navigation from "@/components/Navigation.vue";
import AppFooter from "@/components/AppFooter.vue";
import { mapState } from "vuex";

export default {
	name: "Home",
	components: {
		Tweet,
		addTweet,
		ListUsers,
		Deconnexion,
		Navigation,
		AppFooter,
	},
	mounted: function () {
		this.$store.dispatch("getTweets");
		this.$store.dispatch("getAllUsers");
	},
	updated: function () {
		this.$store.dispatch("getTweets");
	},
	computed: {
		...mapState({
			tweets: "tweets",
			users: "users",
		}),
	},
};
</script>

<style>
.timeline {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	font-size: 1.5em;
}

.timeline * {
	margin-top: 0.5em;
}
</style>
