package com.ecommerce.project.config;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.User;
import com.ecommerce.project.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(2)
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public DataSeeder(CategoryRepository categoryRepository,
                      ProductRepository productRepository,
                      UserRepository userRepository,
                      CartItemRepository cartItemRepository,
                      CartRepository cartRepository,
                      OrderItemRepository orderItemRepository,
                      OrderRepository orderRepository,
                      PaymentRepository paymentRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (productRepository.count() > 0 && productRepository.count() < 200) {
            System.out.println("DataSeeder: Database has old sample products. Clearing existing data...");
            cartItemRepository.deleteAll();
            cartRepository.deleteAll();
            orderItemRepository.deleteAll();
            orderRepository.deleteAll();
            paymentRepository.deleteAll();
            productRepository.deleteAll();
            categoryRepository.deleteAll();
            System.out.println("DataSeeder: Existing data cleared successfully.");
        } else if (productRepository.count() >= 200) {
            System.out.println("DataSeeder: Database already has expanded product catalog. Skipping seeding.");
            return;
        }

        User admin = userRepository.findByUserName("aditya.vyra").orElse(null);
        if (admin == null) {
            System.out.println("DataSeeder: Admin user not found, skipping product seeding.");
            return;
        }

        // Create categories
        Category electronics = createCategory("Electronics");
        Category homeAppliances = createCategory("Home Appliances");
        Category fashion = createCategory("Fashion");
        Category beauty = createCategory("Beauty & Personal Care");
        Category furniture = createCategory("Furniture & Home Living");
        Category sports = createCategory("Sports & Fitness");
        Category grocery = createCategory("Grocery");
        Category automotive = createCategory("Automotive");
        Category books = createCategory("Books");
        Category toys = createCategory("Toys & Baby");

        // ==================== ELECTRONICS (25 products) ====================
        createProduct("Apple iPhone 15 Pro Max", "Titanium design, A17 Pro chip, 48MP camera system with 5x optical zoom, USB-C, and Action button.", 15, 134900.0, 8.0, electronics, admin, "/products/apple_iphone_15_pro_max.jpg");
        createProduct("Samsung Galaxy S24 Ultra", "Galaxy AI powered smartphone with 200MP camera, built-in S Pen, Titanium frame, and Snapdragon 8 Gen 3.", 20, 129999.0, 10.0, electronics, admin, "/products/samsung_galaxy_s24_ultra.jpg");
        createProduct("MacBook Pro 16-inch M3", "M3 Max chip, 16.2-inch Liquid Retina XDR display, up to 22 hours battery life, Thunderbolt 4 ports.", 10, 249900.0, 5.0, electronics, admin, "/products/macbook_pro_16_inch_m3.jpg");
        createProduct("Dell XPS 15 Laptop", "Intel Core i7 13th Gen, 15.6-inch OLED 3.5K display, 16GB RAM, 512GB SSD, sleek InfinityEdge design.", 12, 145990.0, 10.0, electronics, admin, "/products/dell_xps_15_laptop.jpg");
        createProduct("iPad Air M2", "10.9-inch Liquid Retina display, M2 chip, 12MP Wide camera, Touch ID, USB-C connectivity.", 18, 74900.0, 8.0, electronics, admin, "/products/ipad_air_m2.jpg");
        createProduct("Sony WH-1000XM5 Headphones", "Industry-leading noise cancellation, 30-hour battery, crystal clear hands-free calling, multipoint connection.", 35, 29990.0, 12.0, electronics, admin, "/products/sony_wh_1000xm5_headphones.jpg");
        createProduct("Apple Watch Series 9", "S9 SiP chip, double tap gesture, brighter 2000 nit display, advanced health and fitness tracking.", 30, 41900.0, 10.0, electronics, admin, "/products/apple_watch_series_9.jpg");
        createProduct("Samsung Galaxy Tab S9", "11-inch Dynamic AMOLED 2X display, Snapdragon 8 Gen 2, S Pen included, IP68 water resistance.", 22, 72999.0, 8.0, electronics, admin, "/products/samsung_galaxy_tab_s9.jpg");
        createProduct("Sony PlayStation 5 Slim", "Ultra-high speed SSD, ray tracing, haptic feedback, 4K gaming console with DualSense controller.", 12, 49990.0, 5.0, electronics, admin, "/products/sony_playstation_5_slim.jpg");
        createProduct("Canon EOS R50 Camera", "Compact mirrorless camera with 24.2MP APS-C CMOS sensor, 4K video recording, and Dual Pixel CMOS AF.", 14, 68990.0, 10.0, electronics, admin, "/products/canon_eos_r50_camera.jpg");
        createProduct("JBL Flip 6 Speaker", "Portable Bluetooth speaker with powerful JBL Original Pro Sound, IP67 waterproof, 12-hour playtime.", 50, 9999.0, 5.0, electronics, admin, "/products/jbl_flip_6_speaker.jpg");
        createProduct("Logitech MX Master 3S Mouse", "Wireless performance mouse with 8K DPI sensor, quiet clicks, MagSpeed scroll wheel, USB-C charging.", 40, 8995.0, 10.0, electronics, admin, "/products/logitech_mx_master_3s_mouse.jpg");
        createProduct("Kindle Paperwhite 16GB", "6.8-inch glare-free display, adjustable warm light, waterproof, up to 10 weeks battery life.", 60, 14999.0, 5.0, electronics, admin, "/products/kindle_paperwhite_16gb.jpg");
        createProduct("Nintendo Switch OLED", "7-inch vibrant OLED screen, wide adjustable stand, wired LAN port, enhanced audio.", 20, 32990.0, 10.0, electronics, admin, "/products/nintendo_switch_oled.jpg");
        createProduct("GoPro HERO12 Black", "5.3K60 video, HyperSmooth 6.0 stabilization, waterproof to 33ft, Max Lens Mod 2.0 compatible.", 18, 44990.0, 10.0, electronics, admin, "/products/gopro_hero12_black.jpg");
        createProduct("DJI Mini 4 Pro Drone", "Under 249g, 4K/60fps HDR true vertical shooting, omnidirectional obstacle sensing, 34-min flight.", 11, 74990.0, 5.0, electronics, admin, "/products/dji_mini_4_pro_drone.jpg");
        createProduct("Bose QuietComfort 45", "World-class noise cancelling headphones with high-fidelity audio, 24-hour battery, comfortable fit.", 28, 25990.0, 8.0, electronics, admin, "/products/bose_quietcomfort_45.jpg");
        createProduct("ASUS ROG Zephyrus G14 Laptop", "AMD Ryzen 9, NVIDIA RTX 4060, 14-inch QHD+ 165Hz display, AniMe Matrix LED, gaming powerhouse.", 8, 139990.0, 8.0, electronics, admin, "/products/asus_rog_zephyrus_g14_laptop.jpg");
        createProduct("Samsung 55-inch QLED 4K TV", "Quantum Dot technology, 100% Color Volume, Object Tracking Sound, Smart TV with Tizen OS.", 10, 79990.0, 12.0, electronics, admin, "/products/samsung_55_inch_qled_4k_tv.jpg");
        createProduct("Apple AirPods Pro 2nd Gen", "Active Noise Cancellation, Adaptive Transparency, personalized Spatial Audio, USB-C MagSafe case.", 45, 24900.0, 5.0, electronics, admin, "/products/apple_airpods_pro_2nd_gen.jpg");
        createProduct("Razer BlackShark V2 Pro Headset", "Wireless esports gaming headset with TriForce Titanium 50mm drivers, ultra-soft memory foam cushions.", 30, 14999.0, 15.0, electronics, admin, "/products/razer_blackshark_v2_pro_headset.jpg");
        createProduct("Keychron K2 Wireless Keyboard", "75% layout wireless mechanical keyboard with Gateron switches, RGB backlight, Mac and Windows compatible.", 55, 7999.0, 10.0, electronics, admin, "/products/keychron_k2_wireless_keyboard.jpg");
        createProduct("Samsung T7 2TB Portable SSD", "Portable external solid state drive with USB 3.2 Gen 2, 1050MB/s transfer speed, fingerprint security.", 40, 15990.0, 12.0, electronics, admin, "/products/samsung_t7_2tb_portable_ssd.jpg");
        createProduct("Dell UltraSharp 27 4K Monitor", "27-inch 4K USB-C Hub Monitor with IPS Black technology, 98% DCI-P3, factory calibrated colors.", 16, 42990.0, 10.0, electronics, admin, "/products/dell_ultrasharp_27_4k_monitor.jpg");
        createProduct("Anker 7-in-1 USB-C Hub", "Compact adapter expanding USB-C to HDMI 4K, USB-A 3.0, SD/microSD card slots, 100W Power Delivery.", 100, 3499.0, 5.0, electronics, admin, "/products/anker_7_in_1_usb_c_hub.jpg");

        // ==================== HOME APPLIANCES (20 products) ====================
        createProduct("Samsung 253L Double Door Refrigerator", "Frost free double door refrigerator with digital inverter compressor, convertible 5-in-1 modes.", 15, 26990.0, 10.0, homeAppliances, admin, "/products/samsung_253l_double_door_refrigerator.jpg");
        createProduct("LG 8kg Front Load Washing Machine", "AI Direct Drive front load washer with steam wash, 6 Motion DD technology, energy efficient.", 12, 35990.0, 12.0, homeAppliances, admin, "/products/lg_8kg_front_load_washing_machine.jpg");
        createProduct("Daikin 1.5 Ton 5 Star Split AC", "Inverter split air conditioner with PM 2.5 filter, copper condenser, power chill operation.", 20, 42990.0, 8.0, homeAppliances, admin, "/products/daikin_15_ton_5_star_split_ac.jpg");
        createProduct("Kent Grand Plus Water Purifier", "RO+UV+UF water purifier with TDS controller, 8L storage tank, mineral RO technology.", 30, 15999.0, 10.0, homeAppliances, admin, "/products/kent_grand_plus_water_purifier.jpg");
        createProduct("Samsung 28L Convection Microwave", "Convection microwave oven with SlimFry technology, ceramic enamel cavity, 28-litre capacity.", 25, 14990.0, 8.0, homeAppliances, admin, "/products/samsung_28l_convection_microwave.jpg");
        createProduct("Dyson V15 Detect Vacuum Cleaner", "Intelligent cordless vacuum with laser dust detection, piezo sensor, LCD screen showing particle count.", 8, 62900.0, 5.0, homeAppliances, admin, "/products/dyson_v15_detect_vacuum_cleaner.jpg");
        createProduct("Bajaj Majesty 2200W Induction Cooktop", "2200-watt induction cooktop with feather touch buttons, auto-off, pan sensor technology.", 50, 2299.0, 5.0, homeAppliances, admin, "/products/bajaj_majesty_2200w_induction_cooktop.jpg");
        createProduct("Philips 1500W Steam Iron", "Powerful steam iron with non-stick soleplate, continuous steam output, anti-drip technology.", 45, 2499.0, 10.0, homeAppliances, admin, "/products/philips_1500w_steam_iron.jpg");
        createProduct("Havells Instanio 25L Water Heater", "25-litre storage water heater with Whirl Flow technology, color-changing LED indicator, heavy duty anode rod.", 18, 8990.0, 8.0, homeAppliances, admin, "/products/havells_instanio_25l_water_heater.jpg");
        createProduct("Crompton Ozone 75L Desert Air Cooler", "75-litre desert cooler with honeycomb pads, ice chamber, auto-fill with water level indicator.", 14, 10999.0, 10.0, homeAppliances, admin, "/products/crompton_ozone_75l_desert_air_cooler.jpg");
        createProduct("Bosch 12 Place Dishwasher", "Free-standing dishwasher with EcoSilence Drive, AquaStop leak protection, 6 wash programmes.", 10, 38990.0, 12.0, homeAppliances, admin, "/products/bosch_12_place_dishwasher.jpg");
        createProduct("Prestige 750W Mixer Grinder", "750-watt mixer grinder with 3 stainless steel jars, super efficient motor, ergonomic handles.", 60, 3499.0, 5.0, homeAppliances, admin, "/products/prestige_750w_mixer_grinder.jpg");
        createProduct("Philips 2-Slice Toaster", "Compact toaster with 8 browning settings, integrated bun warming rack, high lift feature.", 40, 2195.0, 8.0, homeAppliances, admin, "/products/philips_2_slice_toaster.jpg");
        createProduct("Nespresso Vertuo Coffee Machine", "One-touch brewing coffee and espresso machine with centrifusion technology, 5 cup sizes.", 15, 16990.0, 15.0, homeAppliances, admin, "/products/nespresso_vertuo_coffee_machine.jpg");
        createProduct("Eureka Forbes Quick Clean Vacuum", "Lightweight handheld vacuum cleaner with HEPA filter, multi-surface cleaning, washable dust bag.", 35, 5999.0, 10.0, homeAppliances, admin, "/products/eureka_forbes_quick_clean_vacuum.jpg");
        createProduct("Voltas 1.5 Ton Window AC", "1.5 ton 3-star window air conditioner with copper condenser, anti-dust filter, sleep mode.", 16, 28990.0, 8.0, homeAppliances, admin, "/products/voltas_15_ton_window_ac.jpg");
        createProduct("LG 687L Side-by-Side Refrigerator", "Side-by-side refrigerator with InstaView Door-in-Door, linear cooling, hygiene fresh plus.", 6, 65990.0, 10.0, homeAppliances, admin, "/products/lg_687l_side_by_side_refrigerator.jpg");
        createProduct("IFB 6.5kg Top Load Washer", "Fully automatic top load washing machine with aqua energie, smart sense, 3D wash system.", 20, 18990.0, 12.0, homeAppliances, admin, "/products/ifb_65kg_top_load_washer.jpg");
        createProduct("Morphy Richards OTG 52L Oven", "52-litre oven toaster griller with convection fan, motorized rotisserie, 6 heating modes.", 18, 11999.0, 10.0, homeAppliances, admin, "/products/morphy_richards_otg_52l_oven.jpg");
        createProduct("Philips Hue Smart Bulb Starter Kit", "Smart LED starter kit with 3 color ambiance bulbs, Hue Bridge, voice control with Alexa and Google.", 25, 9999.0, 5.0, homeAppliances, admin, "/products/philips_hue_smart_bulb_starter_kit.jpg");

        // ==================== FASHION (25 products) ====================
        createProduct("Levi's 511 Slim Fit Jeans", "Modern slim fit jeans with stretch denim construction, classic 5-pocket styling, versatile dark wash.", 80, 3999.0, 15.0, fashion, admin, "/products/levis_511_slim_fit_jeans.jpg");
        createProduct("Nike Air Force 1 Sneakers", "Iconic basketball shoe design with soft springy cushioning, durable leather upper, Air-Sole unit.", 70, 8195.0, 5.0, fashion, admin, "/products/nike_air_force_1_sneakers.jpg");
        createProduct("Uniqlo Organic Cotton T-Shirt", "Ultra-soft unisex t-shirt made from 100% certified organic cotton, crew neck, relaxed fit.", 150, 999.0, 5.0, fashion, admin, "/products/uniqlo_organic_cotton_t_shirt.jpg");
        createProduct("Adidas Originals Track Jacket", "Retro style track jacket with classic three-stripes design, recycled polyester, iconic Trefoil logo.", 65, 5999.0, 10.0, fashion, admin, "/products/adidas_originals_track_jacket.jpg");
        createProduct("Women's Floral Summer Dress", "A-line floral print summer dress in lightweight chiffon fabric, adjustable spaghetti straps.", 55, 2499.0, 10.0, fashion, admin, "/products/womens_floral_summer_dress.jpg");
        createProduct("Premium Leather Biker Jacket", "Classic genuine leather jacket with asymmetric zipper, quilted shoulders, satin lining.", 20, 12999.0, 20.0, fashion, admin, "/products/premium_leather_biker_jacket.jpg");
        createProduct("Ray-Ban Classic Wayfarer", "Iconic G-15 polarized lenses with durable acetate frame, UV400 protection, timeless design.", 50, 10990.0, 12.0, fashion, admin, "/products/ray_ban_classic_wayfarer.jpg");
        createProduct("Tommy Hilfiger Polo Shirt", "Classic fit mesh polo shirt with embroidered flag logo, two-button placket, ribbed collar.", 45, 4499.0, 10.0, fashion, admin, "/products/tommy_hilfiger_polo_shirt.jpg");
        createProduct("Nike Air Max 270 Shoes", "Lifestyle shoe featuring Nike's tallest Air unit yet, breathable mesh upper, foam midsole.", 50, 12995.0, 10.0, fashion, admin, "/products/nike_air_max_270_shoes.jpg");
        createProduct("Puma Classic Suede Sneakers", "The emblem of Puma history, soft suede upper with classic formstrip, rubber cupsole.", 90, 5499.0, 5.0, fashion, admin, "/products/puma_classic_suede_sneakers.jpg");
        createProduct("Men's Slim Fit Oxford Shirt", "Classic cotton slim-fit button-down Oxford shirt with chest pocket, wrinkle-resistant fabric.", 60, 2299.0, 10.0, fashion, admin, "/products/mens_slim_fit_oxford_shirt.jpg");
        createProduct("Women's Suede Ankle Boots", "Stylish suede leather ankle boots with comfortable block heel, side zipper, cushioned insole.", 40, 6999.0, 15.0, fashion, admin, "/products/womens_suede_ankle_boots.jpg");
        createProduct("Champion Reverse Weave Hoodie", "Heavyweight fleece hoodie with shrink-resistant reverse weave construction, iconic C logo.", 85, 4999.0, 10.0, fashion, admin, "/products/champion_reverse_weave_hoodie.jpg");
        createProduct("Ralph Lauren Classic Polo", "Classic fit mesh polo shirt with signature embroidered pony logo, ribbed cuffs, tennis tail.", 75, 6990.0, 10.0, fashion, admin, "/products/ralph_lauren_classic_polo.jpg");
        createProduct("Women's High-Waisted Leggings", "Buttery soft high-rise yoga leggings with weightless feel, 4-way stretch, hidden waistband pocket.", 110, 1999.0, 0.0, fashion, admin, "/products/womens_high_waisted_leggings.jpg");
        createProduct("Casio G-Shock Digital Watch", "Shock-resistant digital watch with 200m water resistance, world time, LED backlight, durable resin band.", 35, 7995.0, 8.0, fashion, admin, "/products/casio_g_shock_digital_watch.jpg");
        createProduct("Men's Wool Blend Pea Coat", "Double-breasted classic pea coat in warm wool blend fabric, notch lapel, anchor buttons.", 25, 8999.0, 15.0, fashion, admin, "/products/mens_wool_blend_pea_coat.jpg");
        createProduct("Calvin Klein Cotton Briefs Pack", "Classic cotton stretch underwear pack of 3 with signature waistband, breathable cotton blend.", 120, 2799.0, 12.0, fashion, admin, "/products/calvin_klein_cotton_briefs_pack.jpg");
        createProduct("Under Armour Training Joggers", "Light, breathable and stretchy fleece joggers for superior mobility, tapered leg, zippered pockets.", 95, 3499.0, 8.0, fashion, admin, "/products/under_armour_training_joggers.jpg");
        createProduct("Designer Leather Handbag", "Elegant shoulder handbag made from soft pebbled leather, gold-tone hardware, multiple compartments.", 30, 8999.0, 15.0, fashion, admin, "/products/designer_leather_handbag.jpg");
        createProduct("Women's Ribbed Knit Crop Top", "Short-sleeve ribbed knit crop top with stretchy fit, round neck, available in multiple colors.", 130, 899.0, 0.0, fashion, admin, "/products/womens_ribbed_knit_crop_top.jpg");
        createProduct("Tommy Hilfiger Analog Watch", "Men's quartz watch with stainless steel case, leather strap, 50m water resistance, date display.", 40, 8995.0, 10.0, fashion, admin, "/products/tommy_hilfiger_analog_watch.jpg");
        createProduct("Fossil Grant Chronograph Watch", "Chronograph watch with genuine leather band, Roman numeral markers, 24-hour sub-dial, date window.", 35, 10995.0, 10.0, fashion, admin, "/products/fossil_grant_chronograph_watch.jpg");
        createProduct("H&M Slim Fit Chinos", "Slim fit chinos in stretch cotton twill, side pockets, back welt pockets, comfortable everyday wear.", 100, 1799.0, 5.0, fashion, admin, "/products/hm_slim_fit_chinos.jpg");
        createProduct("Woodland Leather Chelsea Boots", "Genuine leather Chelsea boots with elastic side panels, pull tab, durable rubber outsole.", 45, 5495.0, 10.0, fashion, admin, "/products/woodland_leather_chelsea_boots.jpg");

        // ==================== BEAUTY & PERSONAL CARE (20 products) ====================
        createProduct("L'Oreal Revitalift Serum", "1.5% Pure Hyaluronic Acid serum to intensely hydrate, replump skin, reduce wrinkles visibly.", 80, 999.0, 15.0, beauty, admin, "/products/loreal_revitalift_serum.jpg");
        createProduct("Maybelline Fit Me Foundation", "Matte + Poreless liquid foundation for normal to oily skin, natural finish, lightweight formula.", 130, 549.0, 10.0, beauty, admin, "/products/maybelline_fit_me_foundation.jpg");
        createProduct("Neutrogena Hydro Boost Gel", "Hyaluronic acid water gel moisturizer for dry skin, oil-free, non-comedogenic, instant hydration.", 90, 1199.0, 10.0, beauty, admin, "/products/neutrogena_hydro_boost_gel.jpg");
        createProduct("Lakme 9to5 Primer + Matte Lipstick", "Long-lasting matte lipstick with built-in primer, enriched with Vitamin E, smooth application.", 140, 499.0, 5.0, beauty, admin, "/products/lakme_9to5_primer_matte_lipstick.jpg");
        createProduct("Dove Deep Moisture Body Wash", "Nourishing body wash with NutriumMoisture technology, mild cleansers, dermatologist recommended.", 100, 349.0, 8.0, beauty, admin, "/products/dove_deep_moisture_body_wash.jpg");
        createProduct("Nivea Soft Moisturizing Cream", "Intensively moisturizing cream with Vitamin E and Jojoba oil, non-greasy formula, for face and body.", 120, 259.0, 5.0, beauty, admin, "/products/nivea_soft_moisturizing_cream.jpg");
        createProduct("Olaplex No.4 Bond Repair Shampoo", "Highly moisturizing reparative shampoo for all hair types, repairs damaged hair, reduces breakage.", 70, 2800.0, 10.0, beauty, admin, "/products/olaplex_no4_bond_repair_shampoo.jpg");
        createProduct("CeraVe Hydrating Facial Cleanser", "Non-foaming face wash with 3 essential ceramides and hyaluronic acid, gentle formula for dry skin.", 85, 1399.0, 8.0, beauty, admin, "/products/cerave_hydrating_facial_cleanser.jpg");
        createProduct("Dyson Airwrap Multi-Styler", "Style with air, not extreme heat. Dry, curl, shape, smooth hair with Coanda airflow technology.", 10, 44900.0, 5.0, beauty, admin, "/products/dyson_airwrap_multi_styler.jpg");
        createProduct("Bleu de Chanel Eau de Parfum", "Woody aromatic fragrance for men with citrus, cedar, and sandalwood notes, long-lasting 100ml.", 25, 8500.0, 0.0, beauty, admin, "/products/bleu_de_chanel_eau_de_parfum.jpg");
        createProduct("The Ordinary Niacinamide Serum", "High-strength 10% Niacinamide + 1% Zinc formula for blemish-prone skin, minimizes pore appearance.", 200, 590.0, 0.0, beauty, admin, "/products/the_ordinary_niacinamide_serum.jpg");
        createProduct("Estee Lauder Night Repair Serum", "Advanced night repair synchronized multi-recovery complex serum, 7 key benefits, all skin types.", 30, 4999.0, 12.0, beauty, admin, "/products/estee_lauder_night_repair_serum.jpg");
        createProduct("Clinique Moisture Surge 100H", "Auto-replenishing hydrator with aloe bio-ferment, lipid-rich cream gel, 100-hour hydration.", 45, 3200.0, 10.0, beauty, admin, "/products/clinique_moisture_surge_100h.jpg");
        createProduct("Kiehl's Ultra Facial Cream", "24-hour daily lightweight facial moisturizer with squalane and glacial glycoprotein, all skin types.", 50, 2900.0, 5.0, beauty, admin, "/products/kiehls_ultra_facial_cream.jpg");
        createProduct("La Roche-Posay SPF 60 Sunscreen", "Ultra-light fluid facial sunscreen with Cell-Ox Shield technology, broad spectrum UVA/UVB protection.", 75, 1799.0, 10.0, beauty, admin, "/products/la_roche_posay_spf_60_sunscreen.jpg");
        createProduct("Fenty Beauty Gloss Bomb", "Universal lip luminizer with explosive shine, shea butter enriched, non-sticky, one shade fits all.", 60, 1600.0, 5.0, beauty, admin, "/products/fenty_beauty_gloss_bomb.jpg");
        createProduct("Philips BHD356 Hair Dryer", "Professional hair dryer with ThermoProtect technology, 2100W powerful airflow, 6 heat/speed settings.", 40, 2199.0, 8.0, beauty, admin, "/products/philips_bhd356_hair_dryer.jpg");
        createProduct("12-Piece Makeup Brush Set", "Professional synthetic cosmetic brushes with vegan leather travel case, soft bristles, ergonomic handles.", 65, 1499.0, 15.0, beauty, admin, "/products/12_piece_makeup_brush_set.jpg");
        createProduct("Laneige Lip Sleeping Mask", "Leave-on lip mask with Berry Mix Complex that delivers intense moisture overnight, 20g jar.", 100, 1350.0, 10.0, beauty, admin, "/products/laneige_lip_sleeping_mask.jpg");
        createProduct("Urban Decay All Nighter Setting Spray", "Award-winning makeup setting spray for up to 16-hour wear, lightweight microfine mist, oil-free.", 55, 2500.0, 8.0, beauty, admin, "/products/urban_decay_all_nighter_setting_spray.jpg");

        // ==================== FURNITURE (20 products) ====================
        createProduct("Modern Velvet 3-Seater Sofa", "Tufted cushions velvet sofa with sturdy tapered wooden legs, high-density foam, removable covers.", 10, 45999.0, 15.0, furniture, admin, "/products/modern_velvet_3_seater_sofa.jpg");
        createProduct("Ergonomic Office Chair", "High-back office desk chair with adjustable lumbar support, breathable mesh, 3D armrests, tilt lock.", 25, 15999.0, 10.0, furniture, admin, "/products/ergonomic_office_chair.jpg");
        createProduct("Solid Oak Dining Table 6-Seater", "Durable solid oak rectangular dining table for 6, hand-finished top, sturdy trestle base.", 8, 32999.0, 12.0, furniture, admin, "/products/solid_oak_dining_table_6_seater.jpg");
        createProduct("Queen Size Platform Bed Frame", "Metal platform bed frame with wooden slatted headboard, noise-free design, under-bed storage space.", 12, 22999.0, 10.0, furniture, admin, "/products/queen_size_platform_bed_frame.jpg");
        createProduct("5-Tier Industrial Bookshelf", "Industrial rustic wood and black metal open bookshelf, 5 spacious shelves, wall-mountable.", 15, 8999.0, 15.0, furniture, admin, "/products/5_tier_industrial_bookshelf.jpg");
        createProduct("Minimalist Tripod Floor Lamp", "Wooden tripod floor lamp with fabric drum shade, warm ambient lighting, adjustable height.", 30, 4999.0, 5.0, furniture, admin, "/products/minimalist_tripod_floor_lamp.jpg");
        createProduct("Modern Abstract Area Rug", "Soft pile abstract geometric pattern large area rug, non-slip backing, stain-resistant, 6x9 feet.", 20, 7999.0, 20.0, furniture, admin, "/products/modern_abstract_area_rug.jpg");
        createProduct("Wooden 3-Door Wardrobe", "Solid sheesham wood 3-door wardrobe with mirror, hanging space, shelves, and drawer storage.", 6, 28999.0, 10.0, furniture, admin, "/products/wooden_3_door_wardrobe.jpg");
        createProduct("L-Shaped Computer Desk", "Spacious L-shaped corner desk with monitor stand, cable management holes, engineered wood top.", 18, 12999.0, 8.0, furniture, admin, "/products/l_shaped_computer_desk.jpg");
        createProduct("Cotton Throw Pillows Set of 2", "Soft decorative cotton accent pillows with hidden zipper, machine washable covers, 18x18 inches.", 90, 1499.0, 10.0, furniture, admin, "/products/cotton_throw_pillows_set_of_2.jpg");
        createProduct("Ceramic Flower Vase Set of 3", "Handcrafted minimalist white ceramic vases in 3 different sizes, perfect for dried flowers and decor.", 50, 2499.0, 10.0, furniture, admin, "/products/ceramic_flower_vase_set_of_3.jpg");
        createProduct("Blackout Window Curtains", "Thermal insulated noise reducing blackout window curtains, grommet top, 2 panels, 52x84 inches.", 100, 1999.0, 5.0, furniture, admin, "/products/blackout_window_curtains.jpg");
        createProduct("Floating Wall Shelves Set of 3", "Rustic wood floating display shelves for storage, invisible mounting hardware, set of 3 sizes.", 80, 1799.0, 0.0, furniture, admin, "/products/floating_wall_shelves_set_of_3.jpg");
        createProduct("Le Creuset Dutch Oven", "Signature enameled cast iron round dutch oven, 5.5 quart, superior heat distribution, oven safe.", 14, 25995.0, 10.0, furniture, admin, "/products/le_creuset_dutch_oven.jpg");
        createProduct("King Size Memory Foam Mattress", "12-inch gel-infused memory foam mattress with cooling technology, CertiPUR-US certified, medium firm.", 10, 35999.0, 15.0, furniture, admin, "/products/king_size_memory_foam_mattress.jpg");
        createProduct("TV Entertainment Unit", "Modern TV stand with storage cabinets and open shelves, fits up to 55-inch TV, cable management.", 16, 14999.0, 10.0, furniture, admin, "/products/tv_entertainment_unit.jpg");
        createProduct("Recliner Chair Brown Leather", "Premium faux leather recliner with padded armrests, footrest, 3-position reclining, cup holder.", 12, 24999.0, 8.0, furniture, admin, "/products/recliner_chair_brown_leather.jpg");
        createProduct("Round Coffee Table Glass Top", "Modern round coffee table with tempered glass top, gold metal frame, minimalist design.", 22, 6999.0, 10.0, furniture, admin, "/products/round_coffee_table_glass_top.jpg");
        createProduct("Shoe Rack 5-Tier Bamboo", "Natural bamboo 5-tier shoe rack organizer, holds 15-20 pairs, eco-friendly, easy assembly.", 40, 3499.0, 5.0, furniture, admin, "/products/shoe_rack_5_tier_bamboo.jpg");
        createProduct("Ring Video Doorbell 4", "HD video doorbell with improved motion detection, pre-roll video preview, two-way talk, night vision.", 30, 13999.0, 10.0, furniture, admin, "/products/ring_video_doorbell_4.jpg");

        // ==================== SPORTS & FITNESS (20 products) ====================
        createProduct("Adidas FIFA Match Football", "Official match ball with seamless textured surface, FIFA Quality Pro certified, thermal bonded.", 100, 2999.0, 10.0, sports, admin, "/products/adidas_fifa_match_football.jpg");
        createProduct("Kookaburra Beast Cricket Bat", "English willow lightweight professional cricket bat, mid-high sweet spot, premium handle grip.", 20, 8999.0, 15.0, sports, admin, "/products/kookaburra_beast_cricket_bat.jpg");
        createProduct("Wilson Pro Staff Tennis Racket", "Precision oriented racket co-designed by Roger Federer, braided graphite and Kevlar, 97 sq in head.", 25, 18999.0, 10.0, sports, admin, "/products/wilson_pro_staff_tennis_racket.jpg");
        createProduct("Adjustable Dumbbells Set", "Pair of adjustable dumbbells from 2.5 to 24 kg each, quick-change dial system, compact design.", 15, 24999.0, 12.0, sports, admin, "/products/adjustable_dumbbells_set.jpg");
        createProduct("Nike Revolution 6 Running Shoes", "Lightweight running shoes with soft foam midsole, breathable mesh upper, durable rubber outsole.", 60, 4495.0, 10.0, sports, admin, "/products/nike_revolution_6_running_shoes.jpg");
        createProduct("Premium Yoga Mat with Strap", "Extra thick 6mm yoga and exercise mat with carry strap, non-slip textured surface, eco-friendly TPE.", 90, 1499.0, 5.0, sports, admin, "/products/premium_yoga_mat_with_strap.jpg");
        createProduct("Garmin Forerunner 265 GPS Watch", "GPS running smartwatch with AMOLED touchscreen, training readiness, race predictor, 13-day battery.", 18, 37990.0, 10.0, sports, admin, "/products/garmin_forerunner_265_gps_watch.jpg");
        createProduct("Hydro Flask 32oz Water Bottle", "Double wall vacuum insulated stainless steel bottle, keeps cold 24h and hot 12h, BPA-free.", 110, 3495.0, 5.0, sports, admin, "/products/hydro_flask_32oz_water_bottle.jpg");
        createProduct("Coleman 4-Person Dome Tent", "Dome tent with screen room, WeatherTec system with patented welded floors, easy 10-min setup.", 20, 8999.0, 10.0, sports, admin, "/products/coleman_4_person_dome_tent.jpg");
        createProduct("Spalding NBA Official Basketball", "Official NBA game ball with full-grain leather cover, deep channel design, superior grip and feel.", 80, 1999.0, 0.0, sports, admin, "/products/spalding_nba_official_basketball.jpg");
        createProduct("Theragun Prime Massager", "Smart percussive therapy device with 5 built-in speeds, QuietForce Technology, deep muscle treatment.", 16, 17990.0, 15.0, sports, admin, "/products/theragun_prime_massager.jpg");
        createProduct("Fitbit Charge 6 Tracker", "Advanced fitness and health tracker with built-in GPS, heart rate monitoring, sleep tracking, 7-day battery.", 55, 12999.0, 10.0, sports, admin, "/products/fitbit_charge_6_tracker.jpg");
        createProduct("Schwinn IC4 Indoor Cycling Bike", "Stationary magnetic resistance cycling exercise bike with Bluetooth connectivity, 100 resistance levels.", 8, 49999.0, 15.0, sports, admin, "/products/schwinn_ic4_indoor_cycling_bike.jpg");
        createProduct("Callaway Strata Golf Club Set", "Complete golf package set of 12 clubs with stand bag, covers, perfect for beginners and intermediates.", 12, 29999.0, 8.0, sports, admin, "/products/callaway_strata_golf_club_set.jpg");
        createProduct("CamelBak Hydration Pack 2.5L", "Hydration pack backpack with 2.5-litre reservoir, adjustable sternum strap, lightweight ventilated back.", 35, 4999.0, 10.0, sports, admin, "/products/camelbak_hydration_pack_25l.jpg");
        createProduct("Resistance Bands Set of 5", "Set of 5 colour-coded resistance bands with different tension levels, latex-free TPE, carry bag included.", 150, 799.0, 0.0, sports, admin, "/products/resistance_bands_set_of_5.jpg");
        createProduct("Yonex Badminton Racket", "Lightweight isometric head shape badminton racket with Nanomesh Neo frame, built-in T-joint.", 40, 3999.0, 10.0, sports, admin, "/products/yonex_badminton_racket.jpg");
        createProduct("TRX Suspension Training System", "All-in-one suspension training system for full body workouts, anchors to doors, trees, or racks.", 22, 12999.0, 10.0, sports, admin, "/products/trx_suspension_training_system.jpg");
        createProduct("YETI Hopper Flip Portable Cooler", "Leakproof tough soft-sided portable cooler bag with DryHide shell, ColdCell insulation, wide mouth.", 14, 17999.0, 5.0, sports, admin, "/products/yeti_hopper_flip_portable_cooler.jpg");
        createProduct("Oakley Radar EV Path Sunglasses", "Performance sport sunglasses with Prizm Road lenses, extended field of view, lightweight O-Matter frame.", 30, 14990.0, 10.0, sports, admin, "/products/oakley_radar_ev_path_sunglasses.jpg");

        // ==================== GROCERY (20 products) ====================
        createProduct("Tata Gold Tea 500g", "Premium Assam tea blend with rich golden colour and strong taste, 500g vacuum-sealed pack.", 200, 270.0, 5.0, grocery, admin, "/products/tata_gold_tea_500g.jpg");
        createProduct("Aashirvaad Atta 10kg", "100% whole wheat atta with 0% maida, high fibre, makes soft and fluffy rotis, 10kg family pack.", 150, 449.0, 3.0, grocery, admin, "/products/aashirvaad_atta_10kg.jpg");
        createProduct("Fortune Sunflower Oil 5L", "Refined sunflower cooking oil rich in Vitamin E, light and healthy, suitable for all cooking styles.", 120, 599.0, 5.0, grocery, admin, "/products/fortune_sunflower_oil_5l.jpg");
        createProduct("Amul Butter 500g", "Pasteurized butter made from fresh cream, rich and creamy taste, perfect for cooking and spreading.", 180, 260.0, 2.0, grocery, admin, "/products/amul_butter_500g.jpg");
        createProduct("Maggi 2-Minute Noodles 12 Pack", "India's favourite instant noodles with masala flavour, ready in 2 minutes, family pack of 12.", 200, 168.0, 0.0, grocery, admin, "/products/maggi_2_minute_noodles_12_pack.jpg");
        createProduct("Nescafe Gold Instant Coffee 200g", "Premium freeze-dried instant coffee with smooth and rich aroma, 100% Arabica and Robusta blend.", 80, 650.0, 8.0, grocery, admin, "/products/nescafe_gold_instant_coffee_200g.jpg");
        createProduct("India Gate Basmati Rice 5kg", "Aged premium basmati rice with extra long grains, aromatic flavour, fluffy texture when cooked.", 100, 599.0, 5.0, grocery, admin, "/products/india_gate_basmati_rice_5kg.jpg");
        createProduct("Cadbury Dairy Milk Silk Gift Pack", "Premium chocolate gift pack with assorted Silk variants, perfect for gifting on special occasions.", 90, 799.0, 10.0, grocery, admin, "/products/cadbury_dairy_milk_silk_gift_pack.jpg");
        createProduct("Saffola Gold Cooking Oil 5L", "Dual seed technology cooking oil with blend of rice bran and sunflower, heart healthy LOSORB technology.", 100, 899.0, 5.0, grocery, admin, "/products/saffola_gold_cooking_oil_5l.jpg");
        createProduct("Organic Honey 500g", "100% pure organic honey, unprocessed and unfiltered, sourced from Himalayan beekeepers, 500g glass jar.", 70, 449.0, 8.0, grocery, admin, "/products/organic_honey_500g.jpg");
        createProduct("Tata Sampann Chana Dal 1kg", "Unpolished chana dal with high protein content, no added preservatives, sourced from premium farms.", 130, 149.0, 0.0, grocery, admin, "/products/tata_sampann_chana_dal_1kg.jpg");
        createProduct("Kellogg's Corn Flakes 875g", "Crunchy corn flakes breakfast cereal fortified with iron, B vitamins, and folic acid, family pack.", 85, 399.0, 5.0, grocery, admin, "/products/kelloggs_corn_flakes_875g.jpg");
        createProduct("Haldiram's Aloo Bhujia 1kg", "Crispy potato noodle snack with authentic Indian spices, perfect tea-time companion, 1kg value pack.", 110, 280.0, 0.0, grocery, admin, "/products/haldirams_aloo_bhujia_1kg.jpg");
        createProduct("Kissan Mixed Fruit Jam 500g", "Made with 100% real fruit ingredients, no artificial flavours, perfect for breakfast toast and parathas.", 120, 165.0, 5.0, grocery, admin, "/products/kissan_mixed_fruit_jam_500g.jpg");
        createProduct("Paper Boat Aam Panna 6 Pack", "Traditional Indian raw mango drink with cumin and mint, no artificial colours, pack of 6 tetra packs.", 80, 180.0, 0.0, grocery, admin, "/products/paper_boat_aam_panna_6_pack.jpg");
        createProduct("Bournvita Health Drink 1kg", "Chocolate flavoured health drink with Vitamin D for strong bones, iron for blood, 1kg jar.", 95, 465.0, 8.0, grocery, admin, "/products/bournvita_health_drink_1kg.jpg");
        createProduct("Catch Spice Masala Box Set", "Complete Indian spice box with 8 essential masalas, stainless steel dabba, kitchen essential.", 60, 349.0, 10.0, grocery, admin, "/products/catch_spice_masala_box_set.jpg");
        createProduct("Britannia Good Day Cookies 600g", "Butter cookies with rich buttery taste and crunchy texture, perfect snack for all ages, 600g pack.", 140, 135.0, 0.0, grocery, admin, "/products/britannia_good_day_cookies_600g.jpg");
        createProduct("MDH Kitchen King Masala 500g", "Aromatic blend of premium spices for vegetables, curries and gravies, 500g family pack.", 100, 225.0, 5.0, grocery, admin, "/products/mdh_kitchen_king_masala_500g.jpg");
        createProduct("Tropicana Orange Juice 1L", "100% pure orange juice with no added sugar, preservatives, or artificial flavours, 1-litre pack.", 110, 110.0, 0.0, grocery, admin, "/products/tropicana_orange_juice_1l.jpg");

        // ==================== AUTOMOTIVE (20 products) ====================
        createProduct("Bosch Car Battery 12V 65Ah", "Maintenance-free car battery with high cold cranking amps, vibration resistant, 48-month warranty.", 20, 7499.0, 8.0, automotive, admin, "/products/bosch_car_battery_12v_65ah.jpg");
        createProduct("3M Car Dashboard Polish 250ml", "Premium interior dashboard polish with UV protection, anti-static formula, fresh fragrance.", 80, 349.0, 5.0, automotive, admin, "/products/3m_car_dashboard_polish_250ml.jpg");
        createProduct("Michelin Primacy 4 Tyres Set", "Set of 4 premium touring tyres with EverGrip technology, excellent wet braking, 50000km warranty.", 10, 24999.0, 10.0, automotive, admin, "/products/michelin_primacy_4_tyres_set.jpg");
        createProduct("Philips RacingVision H4 Bulbs", "Up to 150% brighter halogen headlight bulbs, H4 fitment, pair pack, enhanced road illumination.", 60, 1299.0, 5.0, automotive, admin, "/products/philips_racingvision_h4_bulbs.jpg");
        createProduct("Pioneer AVH Touchscreen Stereo", "6.8-inch touchscreen car stereo with Apple CarPlay, Android Auto, Bluetooth, USB, rear camera input.", 14, 18999.0, 12.0, automotive, admin, "/products/pioneer_avh_touchscreen_stereo.jpg");
        createProduct("70mai A800S Dash Camera", "4K UHD dual-channel dash camera with GPS, night vision, parking surveillance, 140-degree wide angle.", 30, 9999.0, 10.0, automotive, admin, "/products/70mai_a800s_dash_camera.jpg");
        createProduct("Meguiar's Complete Car Care Kit", "All-in-one car detailing kit with wash, wax, tyre gel, interior cleaner, and microfibre towels.", 40, 3999.0, 8.0, automotive, admin, "/products/meguiars_complete_car_care_kit.jpg");
        createProduct("CTEK MXS 5.0 Battery Charger", "Smart 12V battery charger and maintainer with 8-step charging, recondition mode, for cars and bikes.", 25, 8999.0, 10.0, automotive, admin, "/products/ctek_mxs_50_battery_charger.jpg");
        createProduct("Castrol EDGE 5W-30 Engine Oil 4L", "Fully synthetic engine oil with Fluid Titanium Technology, 4-litre pack, suitable for modern engines.", 50, 2499.0, 5.0, automotive, admin, "/products/castrol_edge_5w_30_engine_oil_4l.jpg");
        createProduct("Car Vacuum Cleaner 120W", "Portable 120W car vacuum cleaner with HEPA filter, wet and dry cleaning, 5m power cord, LED light.", 35, 1999.0, 10.0, automotive, admin, "/products/car_vacuum_cleaner_120w.jpg");
        createProduct("Rain-X Silicone Wiper Blades Pair", "Premium silicone wiper blades with water-repellent coating, streak-free wiping, universal fit pair.", 70, 899.0, 0.0, automotive, admin, "/products/rain_x_silicone_wiper_blades_pair.jpg");
        createProduct("Exide Invamore Inverter Battery", "150Ah tall tubular inverter battery with 36-month warranty, fast charging, low maintenance design.", 12, 11999.0, 8.0, automotive, admin, "/products/exide_invamore_inverter_battery.jpg");
        createProduct("Amaron Pro 35Ah Bike Battery", "35Ah maintenance-free motorcycle battery with high cranking power, vibration resistant, 48-month life.", 45, 1599.0, 5.0, automotive, admin, "/products/amaron_pro_35ah_bike_battery.jpg");
        createProduct("Bluetooth FM Transmitter", "Bluetooth 5.0 FM transmitter with dual USB ports, QC3.0 fast charging, hands-free calling, LED display.", 100, 799.0, 0.0, automotive, admin, "/products/bluetooth_fm_transmitter.jpg");
        createProduct("Car Seat Cover Set Universal", "Universal fit car seat cover set with breathable leather, 5-seat full coverage, airbag compatible.", 30, 2999.0, 10.0, automotive, admin, "/products/car_seat_cover_set_universal.jpg");
        createProduct("LED Car Interior Light Strip Kit", "RGB LED strip light kit with app control, music sync, 48 LEDs, USB powered, easy installation.", 90, 699.0, 5.0, automotive, admin, "/products/led_car_interior_light_strip_kit.jpg");
        createProduct("Tyre Inflator Digital Portable", "Digital portable tyre inflator with auto-stop, LED light, 150 PSI, 12V DC, preset pressure function.", 55, 1799.0, 8.0, automotive, admin, "/products/tyre_inflator_digital_portable.jpg");
        createProduct("Car Phone Mount Magnetic", "360-degree rotation magnetic car phone mount, dashboard and air vent compatible, strong neodymium magnets.", 120, 499.0, 0.0, automotive, admin, "/products/car_phone_mount_magnetic.jpg");
        createProduct("Leather Steering Wheel Cover", "Premium genuine leather steering wheel cover with anti-slip design, breathable, universal 15-inch fit.", 80, 599.0, 5.0, automotive, admin, "/products/leather_steering_wheel_cover.jpg");
        createProduct("Boot Organizer Collapsible", "Collapsible car boot organizer with multiple compartments, waterproof base, foldable design, anti-slip.", 60, 999.0, 10.0, automotive, admin, "/products/boot_organizer_collapsible.jpg");

        // ==================== BOOKS (15 products) ====================
        createProduct("Atomic Habits by James Clear", "Practical guide to building good habits and breaking bad ones, with proven strategies for lasting change.", 100, 399.0, 10.0, books, admin, "/products/atomic_habits_by_james_clear.jpg");
        createProduct("Sapiens by Yuval Noah Harari", "A brief history of humankind exploring how Homo sapiens came to dominate Earth through cognitive revolution.", 80, 499.0, 12.0, books, admin, "/products/sapiens_by_yuval_noah_harari.jpg");
        createProduct("The Psychology of Money", "Morgan Housel's timeless lessons on wealth, greed, and happiness through 19 short stories about money.", 90, 350.0, 8.0, books, admin, "/products/the_psychology_of_money.jpg");
        createProduct("Rich Dad Poor Dad", "Robert Kiyosaki's bestselling personal finance book about what the rich teach their kids about money.", 120, 299.0, 5.0, books, admin, "/products/rich_dad_poor_dad.jpg");
        createProduct("Deep Work by Cal Newport", "Rules for focused success in a distracted world, strategies to cultivate deep concentration and productivity.", 70, 399.0, 10.0, books, admin, "/products/deep_work_by_cal_newport.jpg");
        createProduct("Think and Grow Rich", "Napoleon Hill's classic guide to personal achievement and wealth building through 13 principles of success.", 110, 250.0, 0.0, books, admin, "/products/think_and_grow_rich.jpg");
        createProduct("The Alchemist by Paulo Coelho", "A magical fable about following your dreams, Santiago's journey from Andalusia to the Egyptian pyramids.", 95, 299.0, 8.0, books, admin, "/products/the_alchemist_by_paulo_coelho.jpg");
        createProduct("Ikigai: The Japanese Secret", "Discover the Japanese concept of purpose and longevity, practical wisdom for a happy and meaningful life.", 85, 349.0, 5.0, books, admin, "/products/ikigai_the_japanese_secret.jpg");
        createProduct("Thinking Fast and Slow", "Daniel Kahneman's groundbreaking exploration of two systems of thinking that drive our decisions and judgments.", 60, 599.0, 10.0, books, admin, "/products/thinking_fast_and_slow.jpg");
        createProduct("The 5 AM Club by Robin Sharma", "Own your morning, elevate your life with Robin Sharma's revolutionary morning routine framework.", 90, 299.0, 5.0, books, admin, "/products/the_5_am_club_by_robin_sharma.jpg");
        createProduct("Wings of Fire by APJ Abdul Kalam", "Autobiography of India's Missile Man, from humble beginnings in Rameswaram to becoming India's President.", 100, 249.0, 0.0, books, admin, "/products/wings_of_fire_by_apj_abdul_kalam.jpg");
        createProduct("You Can Win by Shiv Khera", "Step-by-step guide to achieving success through positive thinking, building self-esteem, and goal setting.", 80, 199.0, 0.0, books, admin, "/products/you_can_win_by_shiv_khera.jpg");
        createProduct("The Subtle Art of Not Giving", "Mark Manson's counterintuitive approach to living a good life by embracing limitations and uncertainty.", 75, 350.0, 8.0, books, admin, "/products/the_subtle_art_of_not_giving.jpg");
        createProduct("The Lean Startup by Eric Ries", "How today's entrepreneurs use continuous innovation to create radically successful businesses.", 55, 499.0, 10.0, books, admin, "/products/the_lean_startup_by_eric_ries.jpg");
        createProduct("Zero to One by Peter Thiel", "Notes on startups, or how to build the future. Thiel's contrarian thinking on innovation and monopoly.", 65, 399.0, 12.0, books, admin, "/products/zero_to_one_by_peter_thiel.jpg");

        // ==================== TOYS & BABY (15 products) ====================
        createProduct("LEGO City Police Station Set", "Building set with police station, jail cell, helicopter, 2 trucks, 6 minifigures, 668 pieces.", 30, 5999.0, 10.0, toys, admin, "/products/lego_city_police_station_set.jpg");
        createProduct("Hot Wheels 20-Car Gift Pack", "Set of 20 die-cast cars in 1:64 scale, authentic decos, great for gifting and collecting.", 50, 1999.0, 5.0, toys, admin, "/products/hot_wheels_20_car_gift_pack.jpg");
        createProduct("Fisher-Price Laugh & Learn Walker", "Interactive baby walker with 75+ songs, sounds and phrases, sit-to-stand play, fine motor activities.", 20, 3499.0, 8.0, toys, admin, "/products/fisher_price_laugh_learn_walker.jpg");
        createProduct("Nerf Elite 2.0 Blaster", "Motorized blaster with 24-dart drum, fires darts up to 90 feet, tactical rails for customization.", 40, 1799.0, 10.0, toys, admin, "/products/nerf_elite_20_blaster.jpg");
        createProduct("Funskool Monopoly Board Game", "Classic property trading board game for family fun, includes updated tokens and speed die.", 35, 899.0, 5.0, toys, admin, "/products/funskool_monopoly_board_game.jpg");
        createProduct("Crayola 120 Crayon Box", "120 different colours in a sturdy storage box with built-in sharpener, non-toxic, vibrant colours.", 60, 699.0, 0.0, toys, admin, "/products/crayola_120_crayon_box.jpg");
        createProduct("Baby Pampers Premium 72 Count", "Premium care baby diapers with 360-degree cottony softness, up to 12 hours dryness, size 4, 72 count.", 100, 1199.0, 8.0, toys, admin, "/products/baby_pampers_premium_72_count.jpg");
        createProduct("VTech KidiZoom Smartwatch", "Kids' smartwatch with dual cameras, video games, step counter, alarm clock, splash-proof design.", 25, 4999.0, 10.0, toys, admin, "/products/vtech_kidizoom_smartwatch.jpg");
        createProduct("Melissa & Doug Wooden Puzzle Set", "Set of 4 wooden jigsaw puzzles with storage rack, 12 pieces each, colourful educational themes.", 45, 1299.0, 5.0, toys, admin, "/products/melissa_doug_wooden_puzzle_set.jpg");
        createProduct("Baby Johnson's Bath Gift Set", "Complete baby bath care gift set with shampoo, body wash, lotion, powder, and oil, hypoallergenic.", 70, 599.0, 5.0, toys, admin, "/products/baby_johnsons_bath_gift_set.jpg");
        createProduct("Remote Control Monster Truck", "1:16 scale RC monster truck with 4WD, 2.4GHz remote, rechargeable battery, off-road capable.", 35, 2499.0, 10.0, toys, admin, "/products/remote_control_monster_truck.jpg");
        createProduct("Play-Doh 36 Color Mega Pack", "Non-toxic modelling compound mega pack with 36 vibrant colours, 3-ounce cans, endless creativity.", 55, 1499.0, 5.0, toys, admin, "/products/play_doh_36_color_mega_pack.jpg");
        createProduct("Baby Stroller Lightweight Foldable", "Lightweight aluminium frame stroller with one-hand fold, reclining seat, sun canopy, storage basket.", 12, 7999.0, 10.0, toys, admin, "/products/baby_stroller_lightweight_foldable.jpg");
        createProduct("LEGO Technic Bugatti Chiron", "Detailed 1:8 scale Bugatti Chiron model with W16 engine, movable pistons, 3599 pieces for adults.", 8, 9999.0, 15.0, toys, admin, "/products/lego_technic_bugatti_chiron.jpg");
        createProduct("Wooden Building Blocks 100pcs", "Natural wood building blocks set of 100 pieces in various shapes, non-toxic paint, educational toy.", 65, 1499.0, 5.0, toys, admin, "/products/wooden_building_blocks_100pcs.jpg");

        System.out.println("DataSeeder: Successfully seeded 10 categories and " + productRepository.count() + " products.");
    }

    private Category createCategory(String name) {
        Category category = new Category();
        category.setCategoryName(name);
        return categoryRepository.save(category);
    }

    private void createProduct(String name, String description, int quantity,
                               double price, double discount, Category category, User user, String imageName) {
        Product product = new Product();
        product.setProductName(name);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setCategory(category);
        product.setUser(user);
        product.setImage(imageName);

        // calculate special price
        double specialPrice = price - ((discount / 100) * price);
        product.setSpecialPrice(specialPrice);

        productRepository.save(product);
    }
}
