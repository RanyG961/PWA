<template>
	<div class="tweet">
		<!-- <div class="ppUser">
			<img
				:src="tweet.m"
				alt="Profile picture of the user {{ tweet.username }}"
			/>
		</div>
		<div class="">
			<p> -->
		<!-- {{ tweet.content }} -->
		<!-- <span class=""> {{ tweet.username }} </span>
				<span class=""> {{ moment(tweet.createTime).fromNow() }} </span>
			</p>
		</div> -->
		<p>test {{ tweetData.username }}</p>
	</div>
</template>
<script>
// import { mapState } from "vuex";
import moment from "moment";
export default {
	computed: {
		// ...mapState({ tweet: "tweets" }),
		testTweet() {
			return this.$store.state.tweets;
		},
	},
	methods: {
		moment,
	},
	props: {
		tweetData: {
			type: Object,
			default: () => {},
		},
	},
};
</script>
