<template>
	<h1>Timeline</h1>
	<addTweet />
	<Tweet v-for="tweet in tweets" :key="tweet.id" :tweets="tweet" />
	<ListUsers :users="users" />
</template>

<script>
import Tweet from "@/components/Tweet.vue";
import addTweet from "@/components/addTweet.vue";
import ListUsers from "@/components/ListUsers.vue";
import { mapState } from "vuex";

export default {
	name: "Home",
	components: {
		Tweet,
		addTweet,
		ListUsers,
	},
	mounted: function () {
		this.$store.dispatch("getTweets");
		this.$store.dispatch("getAllUsers");
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
</style>
