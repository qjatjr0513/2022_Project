package com.google.android.gms.common;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
public final class AccountPicker {

    /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
    public static class AccountChooserOptions {
        /* access modifiers changed from: private */
        public Account zza;
        /* access modifiers changed from: private */
        public boolean zzb;
        /* access modifiers changed from: private */
        public ArrayList<Account> zzc;
        /* access modifiers changed from: private */
        public ArrayList<String> zzd;
        /* access modifiers changed from: private */
        public boolean zze;
        /* access modifiers changed from: private */
        public String zzf;
        /* access modifiers changed from: private */
        public Bundle zzg;
        /* access modifiers changed from: private */
        public boolean zzh;
        /* access modifiers changed from: private */
        public int zzi;
        /* access modifiers changed from: private */
        public String zzj;
        /* access modifiers changed from: private */
        public boolean zzk;
        /* access modifiers changed from: private */
        public zza zzl;
        /* access modifiers changed from: private */
        public String zzm;
        /* access modifiers changed from: private */
        public boolean zzn;
        /* access modifiers changed from: private */
        public boolean zzo;

        /* compiled from: com.google.android.gms:play-services-basement@@18.0.0 */
        public static class Builder {
            private Account zza;
            private ArrayList<Account> zzb;
            private ArrayList<String> zzc;
            private boolean zzd = false;
            private String zze;
            private Bundle zzf;

            public AccountChooserOptions build() {
                Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
                Preconditions.checkArgument(true, "Consent is only valid for account chip styled account picker");
                AccountChooserOptions accountChooserOptions = new AccountChooserOptions();
                accountChooserOptions.zzd = this.zzc;
                accountChooserOptions.zzc = this.zzb;
                accountChooserOptions.zze = this.zzd;
                accountChooserOptions.zzl = null;
                accountChooserOptions.zzj = null;
                accountChooserOptions.zzg = this.zzf;
                accountChooserOptions.zza = this.zza;
                accountChooserOptions.zzb = false;
                accountChooserOptions.zzh = false;
                accountChooserOptions.zzm = null;
                accountChooserOptions.zzi = 0;
                accountChooserOptions.zzf = this.zze;
                accountChooserOptions.zzk = false;
                accountChooserOptions.zzn = false;
                accountChooserOptions.zzo = false;
                return accountChooserOptions;
            }

            public Builder setAllowableAccounts(List<Account> allowableAccounts) {
                this.zzb = allowableAccounts == null ? null : new ArrayList<>(allowableAccounts);
                return this;
            }

            public Builder setAllowableAccountsTypes(List<String> allowableAccountTypes) {
                this.zzc = allowableAccountTypes == null ? null : new ArrayList<>(allowableAccountTypes);
                return this;
            }

            public Builder setAlwaysShowAccountPicker(boolean z) {
                this.zzd = z;
                return this;
            }

            public Builder setOptionsForAddingAccount(Bundle bundle) {
                this.zzf = bundle;
                return this;
            }

            public Builder setSelectedAccount(Account account) {
                this.zza = account;
                return this;
            }

            public Builder setTitleOverrideText(String str) {
                this.zze = str;
                return this;
            }
        }
    }

    private AccountPicker() {
    }

    @Deprecated
    public static Intent newChooseAccountIntent(Account selectedAccount, ArrayList<Account> allowableAccounts, String[] allowableAccountTypes, boolean alwaysPromptForAccount, String descriptionOverrideText, String addAccountAuthTokenType, String[] addAccountRequiredFeatures, Bundle addAccountOptions) {
        Intent intent = new Intent();
        Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
        intent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", allowableAccounts);
        intent.putExtra("allowableAccountTypes", allowableAccountTypes);
        intent.putExtra("addAccountOptions", addAccountOptions);
        intent.putExtra("selectedAccount", selectedAccount);
        intent.putExtra("alwaysPromptForAccount", alwaysPromptForAccount);
        intent.putExtra("descriptionTextOverride", descriptionOverrideText);
        intent.putExtra("authTokenType", addAccountAuthTokenType);
        intent.putExtra("addAccountRequiredFeatures", addAccountRequiredFeatures);
        intent.putExtra("setGmsCoreAccount", false);
        intent.putExtra("overrideTheme", 0);
        intent.putExtra("overrideCustomTheme", 0);
        intent.putExtra("hostedDomainFilter", (String) null);
        return intent;
    }

    public static Intent newChooseAccountIntent(AccountChooserOptions options) {
        Intent intent = new Intent();
        boolean unused = options.zzk;
        String unused2 = options.zzj;
        Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
        zza unused3 = options.zzl;
        Preconditions.checkArgument(true, "Consent is only valid for account chip styled account picker");
        boolean unused4 = options.zzb;
        Preconditions.checkArgument(true, "Making the selected account non-clickable is only supported for the theme THEME_DAY_NIGHT_GOOGLE_MATERIAL2");
        boolean unused5 = options.zzk;
        intent.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        intent.setPackage("com.google.android.gms");
        intent.putExtra("allowableAccounts", options.zzc);
        if (options.zzd != null) {
            intent.putExtra("allowableAccountTypes", (String[]) options.zzd.toArray(new String[0]));
        }
        intent.putExtra("addAccountOptions", options.zzg);
        intent.putExtra("selectedAccount", options.zza);
        boolean unused6 = options.zzb;
        intent.putExtra("selectedAccountIsNotClickable", false);
        intent.putExtra("alwaysPromptForAccount", options.zze);
        intent.putExtra("descriptionTextOverride", options.zzf);
        boolean unused7 = options.zzh;
        intent.putExtra("setGmsCoreAccount", false);
        String unused8 = options.zzm;
        intent.putExtra("realClientPackage", (String) null);
        int unused9 = options.zzi;
        intent.putExtra("overrideTheme", 0);
        boolean unused10 = options.zzk;
        intent.putExtra("overrideCustomTheme", 0);
        String unused11 = options.zzj;
        intent.putExtra("hostedDomainFilter", (String) null);
        Bundle bundle = new Bundle();
        boolean unused12 = options.zzk;
        zza unused13 = options.zzl;
        boolean unused14 = options.zzn;
        boolean unused15 = options.zzo;
        if (!bundle.isEmpty()) {
            intent.putExtra("first_party_options_bundle", bundle);
        }
        return intent;
    }
}
