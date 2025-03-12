package fr.ttl.atlasdd.utils.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    // Campaign
    CAMPAIGN_NOT_FOUND("Campagne non trouvée"),
    CAMPAIGN_SAVE_ERROR("Erreur lors de la sauvegarde de la campagne"),
    CAMPAIGN_UPDATE_ERROR("Erreur lors de la mise à jour de la campagne"),
    CAMPAIGN_DELETE_ERROR("Erreur lors de la suppression de la campagne"),
    ADD_PLAYER_TO_CAMPAIGN_ERROR("Erreur lors de l'ajout d'un joueur à la campagne"),
    REMOVE_PLAYER_FROM_CAMPAIGN_ERROR("Erreur lors de la suppression d'un joueur de la campagne"),
    ADD_CHARACTER_TO_CAMPAIGN_ERROR("Erreur lors de l'ajout d'un personnage à la campagne"),
    REMOVE_CHARACTER_FROM_CAMPAIGN_ERROR("Erreur lors de la suppression d'un personnage de la campagne"),

    // Campaign Note
    CAMPAIGN_NOTE_NOT_FOUND("Note de campagne non trouvée"),
    CAMPAIGN_NOTE_SAVE_ERROR("Erreur lors de la sauvegarde de la note de campagne"),
    CAMPAIGN_NOTE_UPDATE_ERROR("Erreur lors de la mise à jour de la note de campagne"),
    CAMPAIGN_NOTE_DELETE_ERROR("Erreur lors de la suppression de la note de campagne"),
    CAMPAIGN_RETRIEVE_ERROR("Erreur lors de la récupération des campagnes"),

    // User
    USER_NOT_FOUND("Utilisateur non trouvé"),
    USER_SAVE_ERROR("Erreur lors de la sauvegarde de l'utilisateur"),
    USER_UPDATE_ERROR("Erreur lors de la mise à jour de l'utilisateur"),
    USER_DELETE_ERROR("Erreur lors de la suppression de l'utilisateur"),
    USER_EMAIL_ALREADY_USED("Email déjà utilisé"),
    USER_PSEUDO_ALREADY_USED("Pseudo déjà utilisé"),
    USER_EMAIL_OR_PASSWORD_INVALID("Email ou mot de passe incorrect"),
    USER_EMAIL_NOT_VERIFIED("Vous devez vérifier votre adresse email"),

    // Friends Invitation
    FRIENDS_INVITATION_NOT_FOUND("Invitation d'amis non trouvée"),
    FRIENDS_INVITATION_SAVE_ERROR("Erreur lors de la sauvegarde de l'invitation d'amis"),
    FRIENDS_INVITATION_ACCEPT_ERROR("Erreur lors de l'acceptation de l'invitation d'amis"),
    FRIENDS_INVITATION_DECLINE_ERROR("Erreur lors du refus de l'invitation d'amis"),
    FRIENDS_INVITATION_CANCEL_ERROR("Erreur lors de l'annulation de l'invitation d'amis"),
    FRIENDS_INVITATION_ALREADY_SENT("Invitation déjà envoyée"),

    // Character
    CHARACTER_NOT_FOUND("Personnage non trouvé"),
    CHARACTER_SAVE_ERROR("Erreur lors de la sauvegarde du personnage"),
    CHARACTER_UPDATE_ERROR("Erreur lors de la mise à jour du personnage"),
    CHARACTER_DELETE_ERROR("Erreur lors de la suppression du personnage"),
    ARMOR_NOT_FOUND("Armure non trouvée"),
    ARMOR_SAVE_ERROR("Erreur lors de la sauvegarde de l'armure"),
    ARMOR_UPDATE_ERROR("Erreur lors de la mise à jour de l'armure"),
    BACKGROUND_NOT_FOUND("Background non trouvé"),
    BACKGROUND_SAVE_ERROR("Erreur lors de la sauvegarde du background"),
    BACKGROUND_UPDATE_ERROR("Erreur lors de la mise à jour du background"),
    CLASS_NOT_FOUND("Classe non trouvée"),
    CLASS_SAVE_ERROR("Erreur lors de la sauvegarde de la classe"),
    CLASS_UPDATE_ERROR("Erreur lors de la mise à jour de la classe"),
    RACE_NOT_FOUND("Race non trouvée"),
    RACE_SAVE_ERROR("Erreur lors de la sauvegarde de la race"),
    RACE_UPDATE_ERROR("Erreur lors de la mise à jour de la race"),
    WEAPON_NOT_FOUND("Arme non trouvée"),
    WEAPON_SAVE_ERROR("Erreur lors de la sauvegarde des armes"),
    WEAPON_UPDATE_ERROR("Erreur lors de la mise à jour des armes"),
    SKILL_RETRIEVE_ERROR("Erreur lors de la récupération des compétences"),
    SPELL_RETRIEVE_ERROR("Erreur lors de la récupération des sorts"),
    WEAPON_RETRIEVE_ERROR("Erreur lors de la récupération des armes"),

    // Character Note
    CHARACTER_NOTE_NOT_FOUND("Note de personnage non trouvée"),
    CHARACTER_NOTE_SAVE_ERROR("Erreur lors de la sauvegarde de la note de personnage"),
    CHARACTER_NOTE_UPDATE_ERROR("Erreur lors de la mise à jour de la note de personnage"),
    CHARACTER_NOTE_DELETE_ERROR("Erreur lors de la suppression de la note de personnage");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
