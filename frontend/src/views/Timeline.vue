<template>
	<div>
		<h1>Tweet</h1>
		<div class="">
			<textarea
				v-model="tweetContent"
				name="tweetContent"
				id="tweetContent"
				placeholder="Quoi de neuf le reuf ?"
			></textarea>
			<!-- <input type="file" id="photo" name="photo" accept="image/*" /> -->
			<input v-model="photo" type="text" name="photo" id="photo" />
			<button @click="createTweet()" type="submit">
				Envoyer tweet !
			</button>
		</div>
		<div>
			<button @click="logout()">Déconnexion</button>
		</div>

		<div class="">
			<div v-for="(tweet, index) in tweets" :key="index">
				<p>
					{{ tweet.content }}
					<span> de </span>
					{{ tweet.username }}
				</p>
			</div>
			<!-- <tweet v-for="tweet in tweets" :key="tweet.id" /> -->
			<!-- <Tweet> </Tweet> -->
		</div>

		<div>
			<input
				type="text"
				v-model="searchUsers"
				name="searchUsers"
				id="searchUsers"
				placeholder="Chercher un utilisateur"
			/>
			<div v-for="user in filteredUsers" :key="user.userId">
				<p @click="goProfil(user.username)">{{ user.username }}</p>
			</div>
		</div>
		<!-- <div v-for="(user, index) in users" :key="index">
			<p>{{ user.username }}</p>
		</div> -->
	</div>
</template>
<script>
import { mapState } from "vuex";
// import tweet from "@/components/Tweet.vue";
// import Tweet1 from "../components/Tweet.vue";
export default {
	name: "Timeline",
	components: {
		// eslint-disable-next-line vue/no-unused-components
		// tweet,
	},
	data: () => {
		return {
			tweetContent: "",
			photo: "null",
			searchUsers: "",
			// testUsers: [],
		};
	},
	mounted: function () {
		this.$store.dispatch("getTweets");
		this.$store.dispatch("getAllUsers");
	},
	computed: {
		validatedFields: function () {
			if (this.tweetContent != "") return true;
			return false;
		},
		...mapState({
			tweets: "tweets",
			users: "users",
		}),
		filteredUsers: function () {
			// return this.users;
			const target_copy = JSON.parse(JSON.stringify(this.users));
			console.log(target_copy[1]);
			if (typeof target_copy[1] !== "undefined")
				return Object.values(target_copy[1]).filter((u) =>
					u.username
						.toLowerCase()
						.includes(this.searchUsers.toLowerCase())
				);
			return target_copy;
		},
		// showTweets: function () {
		//     console.log(tweets);
		//     return false;
		// },
	},
	// updated: function () {
	// 	this.$store.dispatch("getTweets");
	// },
	methods: {
		createTweet: function () {
			// const self = this;
			this.$store.dispatch("createTweet", {
				content: this.tweetContent,
				media: this.photo,
			});
		},
		logout: function () {
			this.$store.commit("logout");
			this.$router.push("/");
		},
		goProfil: function (username) {
			// console.log(this.users);
			// const target_copy = JSON.parse(JSON.stringify(this.users));
			console.log(username);
			this.$router.push({
				path: "/ProfileSearch",
				params: {
					param1: username,
				},
			});
		},
	},
};
</script>
