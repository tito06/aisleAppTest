package com.example.myapplication

data class UserResponse(
    val invites: Invites,
    val likes: Likes
)

data class Invites(
    val pending_invitations_count: Int,
    val profiles: List<Profile>,
    val totalPages: Int
)

data class Likes(
    val can_see_profile: Boolean,
    val likes_received_count: Int,
    val profiles: List<ProfileX>
)

data class Profile(
    val approved_time: Double,
    val disapproved_time: Double,
    val general_information: GeneralInformation,
    val has_active_subscription: Boolean,
    val icebreakers: Any,
    val instagram_images: Any,
    val is_facebook_data_fetched: Boolean,
    val last_seen: Any,
    val last_seen_window: String,
    val lat: Double,
    val lng: Double,
    val meetup: Any,
    val online_code: Int,
    val photos: List<Photo>,
    val preferences: List<Preference>,
    val profile_data_list: List<ProfileData>,
    val show_concierge_badge: Boolean,
    val story: Any,
    val user_interests: List<Any>,
    val verification_status: String,
    val work: Work
)

data class ProfileX(
    val avatar: String,
    val first_name: String
)

data class DrinkingV1(
    val id: Int,
    val name: String,
    val name_alias: String
)

data class ExperienceV1(
    val id: Int,
    val name: String,
    val name_alias: String
)

data class Faith(
    val id: Int,
    val name: String
)

data class FieldOfStudyV1(
    val id: Int,
    val name: String
)

data class GeneralInformation(
    val age: Int,
    val cast: Any,
    val date_of_birth: String,
    val date_of_birth_v1: String,
    val diet: Any,
    val drinking_v1: DrinkingV1,
    val faith: Faith,
    val first_name: String,
    val gender: String,
    val height: Int,
    val kid: Any,
    val location: Location,
    val marital_status_v1: MaritalStatusV1,
    val mbti: Any,
    val mother_tongue: MotherTongue,
    val pet: Any,
    val politics: Any,
    val ref_id: String,
    val settle: Any,
    val smoking_v1: SmokingV1,
    val sun_sign_v1: SunSignV1
)

data class HighestQualificationV1(
    val id: Int,
    val name: String,
    val preference_only: Boolean
)

data class IndustryV1(
    val id: Int,
    val name: String,
    val preference_only: Boolean
)

data class Location(
    val full: String,
    val summary: String
)

data class MaritalStatusV1(
    val id: Int,
    val name: String,
    val preference_only: Boolean
)

data class MotherTongue(
    val id: Int,
    val name: String
)

data class Photo(
    val photo: String,
    val photo_id: Int,
    val selected: Boolean,
    val status: String
)

data class Preference(
    val answer_id: Int,
    val id: Int,
    val preference_question: PreferenceQuestion,
    val value: Int
)