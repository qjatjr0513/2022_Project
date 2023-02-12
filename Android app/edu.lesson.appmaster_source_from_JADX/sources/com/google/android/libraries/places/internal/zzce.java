package com.google.android.libraries.places.internal;

import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.AddressComponent;
import com.google.android.libraries.places.api.model.AddressComponents;
import com.google.android.libraries.places.api.model.DayOfWeek;
import com.google.android.libraries.places.api.model.LocalTime;
import com.google.android.libraries.places.api.model.OpeningHours;
import com.google.android.libraries.places.api.model.Period;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlusCode;
import com.google.android.libraries.places.api.model.TimeOfWeek;
import com.google.android.libraries.places.internal.zzch;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@2.5.0 */
final class zzce {
    private static final zzgg<String, Place.BusinessStatus> zza;
    private static final zzgg<String, Place.Type> zzb;

    static {
        zzgf zzgf = new zzgf();
        zzgf.zza("OPERATIONAL", Place.BusinessStatus.OPERATIONAL);
        zzgf.zza("CLOSED_TEMPORARILY", Place.BusinessStatus.CLOSED_TEMPORARILY);
        zzgf.zza("CLOSED_PERMANENTLY", Place.BusinessStatus.CLOSED_PERMANENTLY);
        zza = zzgf.zzb();
        zzgf zzgf2 = new zzgf();
        zzgf2.zza("accounting", Place.Type.ACCOUNTING);
        zzgf2.zza("administrative_area_level_1", Place.Type.ADMINISTRATIVE_AREA_LEVEL_1);
        zzgf2.zza("administrative_area_level_2", Place.Type.ADMINISTRATIVE_AREA_LEVEL_2);
        zzgf2.zza("administrative_area_level_3", Place.Type.ADMINISTRATIVE_AREA_LEVEL_3);
        zzgf2.zza("administrative_area_level_4", Place.Type.ADMINISTRATIVE_AREA_LEVEL_4);
        zzgf2.zza("administrative_area_level_5", Place.Type.ADMINISTRATIVE_AREA_LEVEL_5);
        zzgf2.zza("airport", Place.Type.AIRPORT);
        zzgf2.zza("amusement_park", Place.Type.AMUSEMENT_PARK);
        zzgf2.zza("aquarium", Place.Type.AQUARIUM);
        zzgf2.zza("archipelago", Place.Type.ARCHIPELAGO);
        zzgf2.zza("art_gallery", Place.Type.ART_GALLERY);
        zzgf2.zza("atm", Place.Type.ATM);
        zzgf2.zza("bakery", Place.Type.BAKERY);
        zzgf2.zza("bank", Place.Type.BANK);
        zzgf2.zza("bar", Place.Type.BAR);
        zzgf2.zza("beauty_salon", Place.Type.BEAUTY_SALON);
        zzgf2.zza("bicycle_store", Place.Type.BICYCLE_STORE);
        zzgf2.zza("book_store", Place.Type.BOOK_STORE);
        zzgf2.zza("bowling_alley", Place.Type.BOWLING_ALLEY);
        zzgf2.zza("bus_station", Place.Type.BUS_STATION);
        zzgf2.zza("cafe", Place.Type.CAFE);
        zzgf2.zza("campground", Place.Type.CAMPGROUND);
        zzgf2.zza("car_dealer", Place.Type.CAR_DEALER);
        zzgf2.zza("car_rental", Place.Type.CAR_RENTAL);
        zzgf2.zza("car_repair", Place.Type.CAR_REPAIR);
        zzgf2.zza("car_wash", Place.Type.CAR_WASH);
        zzgf2.zza("casino", Place.Type.CASINO);
        zzgf2.zza("cemetery", Place.Type.CEMETERY);
        zzgf2.zza("church", Place.Type.CHURCH);
        zzgf2.zza("city_hall", Place.Type.CITY_HALL);
        zzgf2.zza("clothing_store", Place.Type.CLOTHING_STORE);
        zzgf2.zza("colloquial_area", Place.Type.COLLOQUIAL_AREA);
        zzgf2.zza("continent", Place.Type.CONTINENT);
        zzgf2.zza("convenience_store", Place.Type.CONVENIENCE_STORE);
        zzgf2.zza("country", Place.Type.COUNTRY);
        zzgf2.zza("courthouse", Place.Type.COURTHOUSE);
        zzgf2.zza("dentist", Place.Type.DENTIST);
        zzgf2.zza("department_store", Place.Type.DEPARTMENT_STORE);
        zzgf2.zza("doctor", Place.Type.DOCTOR);
        zzgf2.zza("drugstore", Place.Type.DRUGSTORE);
        zzgf2.zza("electrician", Place.Type.ELECTRICIAN);
        zzgf2.zza("electronics_store", Place.Type.ELECTRONICS_STORE);
        zzgf2.zza("embassy", Place.Type.EMBASSY);
        zzgf2.zza("establishment", Place.Type.ESTABLISHMENT);
        zzgf2.zza("finance", Place.Type.FINANCE);
        zzgf2.zza("fire_station", Place.Type.FIRE_STATION);
        zzgf2.zza("floor", Place.Type.FLOOR);
        zzgf2.zza("florist", Place.Type.FLORIST);
        zzgf2.zza("food", Place.Type.FOOD);
        zzgf2.zza("funeral_home", Place.Type.FUNERAL_HOME);
        zzgf2.zza("furniture_store", Place.Type.FURNITURE_STORE);
        zzgf2.zza("gas_station", Place.Type.GAS_STATION);
        zzgf2.zza("general_contractor", Place.Type.GENERAL_CONTRACTOR);
        zzgf2.zza("geocode", Place.Type.GEOCODE);
        zzgf2.zza("grocery_or_supermarket", Place.Type.GROCERY_OR_SUPERMARKET);
        zzgf2.zza("gym", Place.Type.GYM);
        zzgf2.zza("hair_care", Place.Type.HAIR_CARE);
        zzgf2.zza("hardware_store", Place.Type.HARDWARE_STORE);
        zzgf2.zza("health", Place.Type.HEALTH);
        zzgf2.zza("hindu_temple", Place.Type.HINDU_TEMPLE);
        zzgf2.zza("home_goods_store", Place.Type.HOME_GOODS_STORE);
        zzgf2.zza("hospital", Place.Type.HOSPITAL);
        zzgf2.zza("insurance_agency", Place.Type.INSURANCE_AGENCY);
        zzgf2.zza("intersection", Place.Type.INTERSECTION);
        zzgf2.zza("jewelry_store", Place.Type.JEWELRY_STORE);
        zzgf2.zza("laundry", Place.Type.LAUNDRY);
        zzgf2.zza("lawyer", Place.Type.LAWYER);
        zzgf2.zza("library", Place.Type.LIBRARY);
        zzgf2.zza("light_rail_station", Place.Type.LIGHT_RAIL_STATION);
        zzgf2.zza("liquor_store", Place.Type.LIQUOR_STORE);
        zzgf2.zza("local_government_office", Place.Type.LOCAL_GOVERNMENT_OFFICE);
        zzgf2.zza("locality", Place.Type.LOCALITY);
        zzgf2.zza("locksmith", Place.Type.LOCKSMITH);
        zzgf2.zza("lodging", Place.Type.LODGING);
        zzgf2.zza("meal_delivery", Place.Type.MEAL_DELIVERY);
        zzgf2.zza("meal_takeaway", Place.Type.MEAL_TAKEAWAY);
        zzgf2.zza("mosque", Place.Type.MOSQUE);
        zzgf2.zza("movie_rental", Place.Type.MOVIE_RENTAL);
        zzgf2.zza("movie_theater", Place.Type.MOVIE_THEATER);
        zzgf2.zza("moving_company", Place.Type.MOVING_COMPANY);
        zzgf2.zza("museum", Place.Type.MUSEUM);
        zzgf2.zza("natural_feature", Place.Type.NATURAL_FEATURE);
        zzgf2.zza("neighborhood", Place.Type.NEIGHBORHOOD);
        zzgf2.zza("night_club", Place.Type.NIGHT_CLUB);
        zzgf2.zza("painter", Place.Type.PAINTER);
        zzgf2.zza("park", Place.Type.PARK);
        zzgf2.zza("parking", Place.Type.PARKING);
        zzgf2.zza("pet_store", Place.Type.PET_STORE);
        zzgf2.zza("pharmacy", Place.Type.PHARMACY);
        zzgf2.zza("physiotherapist", Place.Type.PHYSIOTHERAPIST);
        zzgf2.zza("place_of_worship", Place.Type.PLACE_OF_WORSHIP);
        zzgf2.zza("plumber", Place.Type.PLUMBER);
        zzgf2.zza("plus_code", Place.Type.PLUS_CODE);
        zzgf2.zza("point_of_interest", Place.Type.POINT_OF_INTEREST);
        zzgf2.zza("police", Place.Type.POLICE);
        zzgf2.zza("political", Place.Type.POLITICAL);
        zzgf2.zza("post_box", Place.Type.POST_BOX);
        zzgf2.zza("post_office", Place.Type.POST_OFFICE);
        zzgf2.zza("postal_code_prefix", Place.Type.POSTAL_CODE_PREFIX);
        zzgf2.zza("postal_code_suffix", Place.Type.POSTAL_CODE_SUFFIX);
        zzgf2.zza("postal_code", Place.Type.POSTAL_CODE);
        zzgf2.zza("postal_town", Place.Type.POSTAL_TOWN);
        zzgf2.zza("premise", Place.Type.PREMISE);
        zzgf2.zza("primary_school", Place.Type.PRIMARY_SCHOOL);
        zzgf2.zza("real_estate_agency", Place.Type.REAL_ESTATE_AGENCY);
        zzgf2.zza("restaurant", Place.Type.RESTAURANT);
        zzgf2.zza("roofing_contractor", Place.Type.ROOFING_CONTRACTOR);
        zzgf2.zza("room", Place.Type.ROOM);
        zzgf2.zza("route", Place.Type.ROUTE);
        zzgf2.zza("rv_park", Place.Type.RV_PARK);
        zzgf2.zza("school", Place.Type.SCHOOL);
        zzgf2.zza("secondary_school", Place.Type.SECONDARY_SCHOOL);
        zzgf2.zza("shoe_store", Place.Type.SHOE_STORE);
        zzgf2.zza("shopping_mall", Place.Type.SHOPPING_MALL);
        zzgf2.zza("spa", Place.Type.SPA);
        zzgf2.zza("stadium", Place.Type.STADIUM);
        zzgf2.zza("storage", Place.Type.STORAGE);
        zzgf2.zza("store", Place.Type.STORE);
        zzgf2.zza("street_address", Place.Type.STREET_ADDRESS);
        zzgf2.zza("street_number", Place.Type.STREET_NUMBER);
        zzgf2.zza("sublocality_level_1", Place.Type.SUBLOCALITY_LEVEL_1);
        zzgf2.zza("sublocality_level_2", Place.Type.SUBLOCALITY_LEVEL_2);
        zzgf2.zza("sublocality_level_3", Place.Type.SUBLOCALITY_LEVEL_3);
        zzgf2.zza("sublocality_level_4", Place.Type.SUBLOCALITY_LEVEL_4);
        zzgf2.zza("sublocality_level_5", Place.Type.SUBLOCALITY_LEVEL_5);
        zzgf2.zza("sublocality", Place.Type.SUBLOCALITY);
        zzgf2.zza("subpremise", Place.Type.SUBPREMISE);
        zzgf2.zza("subway_station", Place.Type.SUBWAY_STATION);
        zzgf2.zza("supermarket", Place.Type.SUPERMARKET);
        zzgf2.zza("synagogue", Place.Type.SYNAGOGUE);
        zzgf2.zza("taxi_stand", Place.Type.TAXI_STAND);
        zzgf2.zza("tourist_attraction", Place.Type.TOURIST_ATTRACTION);
        zzgf2.zza("town_square", Place.Type.TOWN_SQUARE);
        zzgf2.zza("train_station", Place.Type.TRAIN_STATION);
        zzgf2.zza("transit_station", Place.Type.TRANSIT_STATION);
        zzgf2.zza("travel_agency", Place.Type.TRAVEL_AGENCY);
        zzgf2.zza("university", Place.Type.UNIVERSITY);
        zzgf2.zza("veterinary_care", Place.Type.VETERINARY_CARE);
        zzgf2.zza("zoo", Place.Type.ZOO);
        zzb = zzgf2.zzb();
    }

    zzce() {
    }

    static <T> List<T> zza(List<T> list) {
        return list != null ? list : new ArrayList();
    }

    static List<Place.Type> zzb(List<String> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        zzhb zzq = ((zzge) list).listIterator(0);
        while (zzq.hasNext()) {
            String str = (String) zzq.next();
            zzgg<String, Place.Type> zzgg = zzb;
            if (zzgg.containsKey(str)) {
                arrayList.add(zzgg.get(str));
            } else {
                z = true;
            }
        }
        if (z) {
            arrayList.add(Place.Type.OTHER);
        }
        return arrayList;
    }

    static final Place zzc(zzch zzch, List<String> list) throws ApiException {
        AddressComponents addressComponents;
        LatLngBounds latLngBounds;
        LatLng latLng;
        Uri uri;
        Integer num;
        ArrayList arrayList;
        OpeningHours openingHours;
        ArrayList arrayList2;
        Period period;
        PhotoMetadata photoMetadata;
        String str;
        int i;
        int i2;
        AddressComponent addressComponent;
        Place.Builder builder = Place.builder();
        builder.setAttributions(list);
        if (zzch != null) {
            zzge<zzch.zza> zzd = zzch.zzd();
            PlusCode plusCode = null;
            if (zzd == null) {
                addressComponents = null;
            } else {
                ArrayList arrayList3 = new ArrayList();
                zzhb<zzch.zza> zzq = zzd.listIterator(0);
                while (zzq.hasNext()) {
                    zzch.zza next = zzq.next();
                    if (next == null) {
                        addressComponent = null;
                    } else {
                        try {
                            AddressComponent.Builder builder2 = AddressComponent.builder(next.zzb(), next.zza());
                            builder2.setShortName(next.zzc());
                            addressComponent = builder2.build();
                        } catch (IllegalStateException | NullPointerException e) {
                            throw zzd(String.format("AddressComponent not properly defined (%s).", new Object[]{e.getMessage()}));
                        }
                    }
                    zzg(arrayList3, addressComponent);
                }
                addressComponents = AddressComponents.newInstance(arrayList3);
            }
            zzch.zzb zza2 = zzch.zza();
            if (zza2 != null) {
                latLng = zze(zza2.zza());
                zzch.zzb.C2509zzb zzb2 = zza2.zzb();
                if (zzb2 == null) {
                    latLngBounds = null;
                } else {
                    LatLng zze = zze(zzb2.zzb());
                    LatLng zze2 = zze(zzb2.zza());
                    latLngBounds = zze != null ? zze2 == null ? null : new LatLngBounds(zze, zze2) : null;
                }
            } else {
                latLng = null;
                latLngBounds = null;
            }
            String zzr = zzch.zzr();
            if (zzr != null) {
                uri = Uri.parse(zzr);
            } else {
                uri = null;
            }
            String zzn = zzch.zzn();
            String concat = zzn != null ? zzn.concat(".png") : null;
            String zzm = zzch.zzm();
            if (zzm != null) {
                try {
                    num = Integer.valueOf(Color.parseColor(zzm));
                } catch (IllegalArgumentException e2) {
                    num = null;
                }
            } else {
                num = null;
            }
            builder.setAddress(zzch.zzl());
            builder.setAddressComponents(addressComponents);
            builder.setBusinessStatus(zza.getOrDefault(zzch.zzk(), null));
            builder.setId(zzch.zzq());
            builder.setLatLng(latLng);
            builder.setName(zzch.zzp());
            builder.setPhoneNumber(zzch.zzo());
            zzge<zzch.zzd> zze3 = zzch.zze();
            if (zze3 != null) {
                arrayList = new ArrayList();
                zzhb<zzch.zzd> zzq2 = zze3.listIterator(0);
                while (zzq2.hasNext()) {
                    zzch.zzd next2 = zzq2.next();
                    if (next2 == null) {
                        photoMetadata = null;
                    } else if (!TextUtils.isEmpty(next2.zzd())) {
                        Integer zzb3 = next2.zzb();
                        Integer zzc = next2.zzc();
                        PhotoMetadata.Builder builder3 = PhotoMetadata.builder(next2.zzd());
                        zzge<String> zza3 = next2.zza();
                        if (zza3 == null || zza3.isEmpty()) {
                            str = "";
                        } else {
                            str = zzfh.zzb(", ").zzc().zze(zza3);
                        }
                        builder3.setAttributions(str);
                        if (zzb3 == null) {
                            i = 0;
                        } else {
                            i = zzb3.intValue();
                        }
                        builder3.setHeight(i);
                        if (zzc == null) {
                            i2 = 0;
                        } else {
                            i2 = zzc.intValue();
                        }
                        builder3.setWidth(i2);
                        photoMetadata = builder3.build();
                    } else {
                        throw zzd("Photo reference not provided for a PhotoMetadata result.");
                    }
                    zzg(arrayList, photoMetadata);
                }
            } else {
                arrayList = null;
            }
            builder.setPhotoMetadatas(arrayList);
            zzch.zzc zzb4 = zzch.zzb();
            if (zzb4 != null) {
                OpeningHours.Builder builder4 = OpeningHours.builder();
                zzge<zzch.zzc.zza> zza4 = zzb4.zza();
                if (zza4 != null) {
                    arrayList2 = new ArrayList();
                    zzhb<zzch.zzc.zza> zzq3 = zza4.listIterator(0);
                    while (zzq3.hasNext()) {
                        zzch.zzc.zza next3 = zzq3.next();
                        if (next3 != null) {
                            Period.Builder builder5 = Period.builder();
                            builder5.setOpen(zzf(next3.zzb()));
                            builder5.setClose(zzf(next3.zza()));
                            period = builder5.build();
                        } else {
                            period = null;
                        }
                        zzg(arrayList2, period);
                    }
                } else {
                    arrayList2 = null;
                }
                builder4.setPeriods(zza(arrayList2));
                builder4.setWeekdayText(zza(zzb4.zzb()));
                openingHours = builder4.build();
            } else {
                openingHours = null;
            }
            builder.setOpeningHours(openingHours);
            zzch.zze zzc2 = zzch.zzc();
            if (zzc2 != null) {
                PlusCode.Builder builder6 = PlusCode.builder();
                builder6.setCompoundCode(zzc2.zza());
                builder6.setGlobalCode(zzc2.zzb());
                plusCode = builder6.build();
            }
            builder.setPlusCode(plusCode);
            builder.setPriceLevel(zzch.zzh());
            builder.setRating(zzch.zzg());
            builder.setTypes(zzb(zzch.zzf()));
            builder.setUserRatingsTotal(zzch.zzi());
            builder.setUtcOffsetMinutes(zzch.zzj());
            builder.setViewport(latLngBounds);
            builder.setWebsiteUri(uri);
            builder.setIconUrl(concat);
            builder.setIconBackgroundColor(num);
        }
        return builder.build();
    }

    private static ApiException zzd(String str) {
        String valueOf = String.valueOf(str);
        return new ApiException(new Status(8, valueOf.length() != 0 ? "Unexpected server error: ".concat(valueOf) : new String("Unexpected server error: ")));
    }

    private static LatLng zze(zzch.zzb.zza zza2) {
        if (zza2 == null || zza2.zza() == null || zza2.zzb() == null) {
            return null;
        }
        return new LatLng(zza2.zza().doubleValue(), zza2.zzb().doubleValue());
    }

    private static TimeOfWeek zzf(zzch.zzc.zzb zzb2) {
        boolean z;
        boolean z2;
        DayOfWeek dayOfWeek;
        LocalTime localTime = null;
        if (zzb2 == null) {
            return null;
        }
        boolean z3 = true;
        if (zzb2.zza() != null) {
            z = true;
        } else {
            z = false;
        }
        zzfm.zze(z, "Unable to convert Pablo response to TimeOfWeek: The \"day\" field is missing.");
        if (zzb2.zzb() != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        zzfm.zze(z2, "Unable to convert Pablo response to TimeOfWeek: The \"time\" field is missing.");
        switch (zzb2.zza().intValue()) {
            case 0:
                dayOfWeek = DayOfWeek.SUNDAY;
                break;
            case 1:
                dayOfWeek = DayOfWeek.MONDAY;
                break;
            case 2:
                dayOfWeek = DayOfWeek.TUESDAY;
                break;
            case 3:
                dayOfWeek = DayOfWeek.WEDNESDAY;
                break;
            case 4:
                dayOfWeek = DayOfWeek.THURSDAY;
                break;
            case 5:
                dayOfWeek = DayOfWeek.FRIDAY;
                break;
            case 6:
                dayOfWeek = DayOfWeek.SATURDAY;
                break;
            default:
                throw new IllegalArgumentException("pabloDayOfWeek can only be an integer between 0 and 6");
        }
        String zzb3 = zzb2.zzb();
        if (zzb3 != null) {
            String format = String.format("Unable to convert %s to LocalTime, must be of format \"hhmm\".", new Object[]{zzb3});
            if (zzb3.length() != 4) {
                z3 = false;
            }
            zzfm.zze(z3, format);
            try {
                localTime = LocalTime.newInstance(Integer.parseInt(zzb3.substring(0, 2)), Integer.parseInt(zzb3.substring(2, 4)));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(format, e);
            }
        }
        return TimeOfWeek.newInstance(dayOfWeek, localTime);
    }

    private static <T> boolean zzg(Collection<T> collection, T t) {
        if (t != null) {
            return collection.add(t);
        }
        return false;
    }
}
