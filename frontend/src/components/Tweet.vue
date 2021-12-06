<template>
	<div>
		<p v-if="tweets.username">
			{{ tweets.content }} by {{ tweets.username }}
		</p>
		<p v-else>{{ tweets.content }} by you</p>
		<span>
			<!-- {{ moment(tweets.createTime).fromNow() }} -->
		</span>
		<div v-if="!tweets.username">
			<div>
				<p
					v-if="tweets.isRetweeted"
					@click="unRtTweet(tweets.tweetId)"
					class="tweet_actions"
				>
					Vous avez retweeté le tweet {{ tweets.tweetId }}
				</p>
				<button
					v-else
					@click="retweetTweet(tweets.tweetId)"
					class="tweet_actions"
				>
					Vous n'avez pas retweeté le tweet
				</button>
				<span> {{ tweets.nbRt }}</span>
			</div>
			<div>
				<p
					v-if="tweets.isFavorited"
					@click="unFavTweet(tweets.tweetId)"
				>
					Vous avez fav le tweet
				</p>
				<button v-else @click="favTweet(tweets.tweetId)">
					Vous n'avez pas fav le tweet
				</button>
				<span>{{ tweets.nbFav }}</span>
			</div>
		</div>
		<div v-else>
			<p
				v-if="tweets.isRetweeted"
				@click="unRtTweet(tweets.id)"
				class="tweet_actions"
			>
				Vous avez retweeté le tweet {{ tweets.id }}
			</p>
			<button
				v-else
				@click="retweetTweet(tweets.id)"
				class="tweet_actions"
			>
				Vous n'avez pas retweeté le tweet
			</button>
			<span> {{ tweets.nbRt }}</span>

			<p v-if="tweets.isFavorited" @click="unFavTweet(tweets.id)">
				Vous avez fav le tweet
			</p>
			<button v-else @click="favTweet(tweets.id)">
				Vous n'avez pas fav le tweet
			</button>
			<span>{{ tweets.nbFav }}</span>
		</div>
		<div v-if="!tweets.username || tweets.username == me.username">
			<button @click="deleteTweet(tweets.tweetId)">
				Supprimer le tweet
			</button>
		</div>
	</div>
	<!-- <p>{{ me }} me, tweet {{ tweets.username }}</p> -->
</template>

<script>
//import moment from "moment";
import { mapState } from "vuex";

export default {
	name: "Tweet",
	components: {},
	props: ["tweets"],
	// mounted: {
	// 	...mapState({
	// 		me: "userInfos",
	// 	}),
	// },
	computed: {
		...mapState({
			me: "userInfos",
		}),
	},
	methods: {
		//moment,
		retweetTweet: function (tweetId) {
			console.log("TweetId " + tweetId);
			this.$store.dispatch("retweetTweet", {
				tweetId: tweetId,
			});
		},
		unRtTweet: function (tweetId) {
			console.log("TweetId " + tweetId);
			this.$store.dispatch("unRtTweet", {
				tweetId: tweetId,
			});
		},
		favTweet: function (tweetId) {
			this.$store.dispatch("favTweet", {
				tweetId: tweetId,
			});
		},
		unFavTweet: function (tweetId) {
			this.$store.dispatch("unFavTweet", {
				tweetId: tweetId,
			});
		},
		deleteTweet: function (tweetId) {
			console.log("TWEET ID : " + tweetId);
			this.$store.dispatch("deleteTweet", {
				tweetId: tweetId,
			});
		},
	},
};
</script>

<style>
</style>
