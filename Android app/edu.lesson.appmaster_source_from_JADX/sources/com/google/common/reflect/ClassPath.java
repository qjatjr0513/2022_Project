package com.google.common.reflect;

import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.StandardSystemProperty;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.p000io.ByteSource;
import com.google.common.p000io.CharSource;
import com.google.common.p000io.Resources;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class ClassPath {
    private static final String CLASS_FILE_NAME_EXTENSION = ".class";
    private static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.m26on(" ").omitEmptyStrings();
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ClassPath.class.getName());
    private final ImmutableSet<ResourceInfo> resources;

    private ClassPath(ImmutableSet<ResourceInfo> resources2) {
        this.resources = resources2;
    }

    public static ClassPath from(ClassLoader classloader) throws IOException {
        ImmutableSet<LocationInfo> locations = locationsFrom(classloader);
        Set<File> scanned = new HashSet<>();
        UnmodifiableIterator<LocationInfo> it = locations.iterator();
        while (it.hasNext()) {
            scanned.add(it.next().file());
        }
        ImmutableSet.Builder<ResourceInfo> builder = ImmutableSet.builder();
        UnmodifiableIterator<LocationInfo> it2 = locations.iterator();
        while (it2.hasNext()) {
            builder.addAll((Iterable) it2.next().scanResources(scanned));
        }
        return new ClassPath(builder.build());
    }

    public ImmutableSet<ResourceInfo> getResources() {
        return this.resources;
    }

    public ImmutableSet<ClassInfo> getAllClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).filter(new Predicate<ClassInfo>(this) {
            public boolean apply(ClassInfo info) {
                return info.isTopLevel();
            }
        }).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses(String packageName) {
        Preconditions.checkNotNull(packageName);
        ImmutableSet.Builder<ClassInfo> builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo classInfo = it.next();
            if (classInfo.getPackageName().equals(packageName)) {
                builder.add((Object) classInfo);
            }
        }
        return builder.build();
    }

    public ImmutableSet<ClassInfo> getTopLevelClassesRecursive(String packageName) {
        Preconditions.checkNotNull(packageName);
        String packagePrefix = new StringBuilder(String.valueOf(packageName).length() + 1).append(packageName).append('.').toString();
        ImmutableSet.Builder<ClassInfo> builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo classInfo = it.next();
            if (classInfo.getName().startsWith(packagePrefix)) {
                builder.add((Object) classInfo);
            }
        }
        return builder.build();
    }

    public static class ResourceInfo {
        private final File file;
        final ClassLoader loader;
        private final String resourceName;

        /* renamed from: of */
        static ResourceInfo m177of(File file2, String resourceName2, ClassLoader loader2) {
            if (resourceName2.endsWith(ClassPath.CLASS_FILE_NAME_EXTENSION)) {
                return new ClassInfo(file2, resourceName2, loader2);
            }
            return new ResourceInfo(file2, resourceName2, loader2);
        }

        ResourceInfo(File file2, String resourceName2, ClassLoader loader2) {
            this.file = (File) Preconditions.checkNotNull(file2);
            this.resourceName = (String) Preconditions.checkNotNull(resourceName2);
            this.loader = (ClassLoader) Preconditions.checkNotNull(loader2);
        }

        public final URL url() {
            URL url = this.loader.getResource(this.resourceName);
            if (url != null) {
                return url;
            }
            throw new NoSuchElementException(this.resourceName);
        }

        public final ByteSource asByteSource() {
            return Resources.asByteSource(url());
        }

        public final CharSource asCharSource(Charset charset) {
            return Resources.asCharSource(url(), charset);
        }

        public final String getResourceName() {
            return this.resourceName;
        }

        /* access modifiers changed from: package-private */
        public final File getFile() {
            return this.file;
        }

        public int hashCode() {
            return this.resourceName.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ResourceInfo)) {
                return false;
            }
            ResourceInfo that = (ResourceInfo) obj;
            if (!this.resourceName.equals(that.resourceName) || this.loader != that.loader) {
                return false;
            }
            return true;
        }

        public String toString() {
            return this.resourceName;
        }
    }

    public static final class ClassInfo extends ResourceInfo {
        private final String className;

        ClassInfo(File file, String resourceName, ClassLoader loader) {
            super(file, resourceName, loader);
            this.className = ClassPath.getClassName(resourceName);
        }

        public String getPackageName() {
            return Reflection.getPackageName(this.className);
        }

        public String getSimpleName() {
            int lastDollarSign = this.className.lastIndexOf(36);
            if (lastDollarSign != -1) {
                return CharMatcher.inRange('0', '9').trimLeadingFrom(this.className.substring(lastDollarSign + 1));
            }
            String packageName = getPackageName();
            if (packageName.isEmpty()) {
                return this.className;
            }
            return this.className.substring(packageName.length() + 1);
        }

        public String getName() {
            return this.className;
        }

        public boolean isTopLevel() {
            return this.className.indexOf(36) == -1;
        }

        public Class<?> load() {
            try {
                return this.loader.loadClass(this.className);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        public String toString() {
            return this.className;
        }
    }

    static ImmutableSet<LocationInfo> locationsFrom(ClassLoader classloader) {
        ImmutableSet.Builder<LocationInfo> builder = ImmutableSet.builder();
        UnmodifiableIterator<Map.Entry<File, ClassLoader>> it = getClassPathEntries(classloader).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<File, ClassLoader> entry = it.next();
            builder.add((Object) new LocationInfo(entry.getKey(), entry.getValue()));
        }
        return builder.build();
    }

    static final class LocationInfo {
        private final ClassLoader classloader;
        final File home;

        LocationInfo(File home2, ClassLoader classloader2) {
            this.home = (File) Preconditions.checkNotNull(home2);
            this.classloader = (ClassLoader) Preconditions.checkNotNull(classloader2);
        }

        public final File file() {
            return this.home;
        }

        public ImmutableSet<ResourceInfo> scanResources() throws IOException {
            return scanResources(new HashSet());
        }

        public ImmutableSet<ResourceInfo> scanResources(Set<File> scannedFiles) throws IOException {
            ImmutableSet.Builder<ResourceInfo> builder = ImmutableSet.builder();
            scannedFiles.add(this.home);
            scan(this.home, scannedFiles, builder);
            return builder.build();
        }

        private void scan(File file, Set<File> scannedUris, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            try {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        scanDirectory(file, builder);
                    } else {
                        scanJar(file, scannedUris, builder);
                    }
                }
            } catch (SecurityException e) {
                Logger access$000 = ClassPath.logger;
                String valueOf = String.valueOf(file);
                String valueOf2 = String.valueOf(e);
                access$000.warning(new StringBuilder(String.valueOf(valueOf).length() + 16 + String.valueOf(valueOf2).length()).append("Cannot access ").append(valueOf).append(": ").append(valueOf2).toString());
            }
        }

        private void scanJar(File file, Set<File> scannedUris, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    UnmodifiableIterator<File> it = ClassPath.getClassPathFromManifest(file, jarFile.getManifest()).iterator();
                    while (it.hasNext()) {
                        File path = it.next();
                        if (scannedUris.add(path.getCanonicalFile())) {
                            scan(path, scannedUris, builder);
                        }
                    }
                    scanJarFile(jarFile, builder);
                } finally {
                    try {
                        jarFile.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
            }
        }

        private void scanJarFile(JarFile file, ImmutableSet.Builder<ResourceInfo> builder) {
            Enumeration<JarEntry> entries = file.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && !entry.getName().equals("META-INF/MANIFEST.MF")) {
                    builder.add((Object) ResourceInfo.m177of(new File(file.getName()), entry.getName(), this.classloader));
                }
            }
        }

        private void scanDirectory(File directory, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            Set<File> currentPath = new HashSet<>();
            currentPath.add(directory.getCanonicalFile());
            scanDirectory(directory, "", currentPath, builder);
        }

        private void scanDirectory(File directory, String packagePrefix, Set<File> currentPath, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            File[] files = directory.listFiles();
            if (files == null) {
                Logger access$000 = ClassPath.logger;
                String valueOf = String.valueOf(directory);
                access$000.warning(new StringBuilder(String.valueOf(valueOf).length() + 22).append("Cannot read directory ").append(valueOf).toString());
                return;
            }
            for (File f : files) {
                String name = f.getName();
                if (f.isDirectory()) {
                    File deref = f.getCanonicalFile();
                    if (currentPath.add(deref)) {
                        scanDirectory(deref, new StringBuilder(String.valueOf(packagePrefix).length() + 1 + String.valueOf(name).length()).append(packagePrefix).append(name).append("/").toString(), currentPath, builder);
                        currentPath.remove(deref);
                    }
                } else {
                    String valueOf2 = String.valueOf(packagePrefix);
                    String valueOf3 = String.valueOf(name);
                    String resourceName = valueOf3.length() != 0 ? valueOf2.concat(valueOf3) : new String(valueOf2);
                    if (!resourceName.equals("META-INF/MANIFEST.MF")) {
                        builder.add((Object) ResourceInfo.m177of(f, resourceName, this.classloader));
                    }
                }
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof LocationInfo)) {
                return false;
            }
            LocationInfo that = (LocationInfo) obj;
            if (!this.home.equals(that.home) || !this.classloader.equals(that.classloader)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.home.hashCode();
        }

        public String toString() {
            return this.home.toString();
        }
    }

    static ImmutableSet<File> getClassPathFromManifest(File jarFile, @NullableDecl Manifest manifest) {
        if (manifest == null) {
            return ImmutableSet.m83of();
        }
        ImmutableSet.Builder<File> builder = ImmutableSet.builder();
        String classpathAttribute = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
        if (classpathAttribute != null) {
            for (String path : CLASS_PATH_ATTRIBUTE_SEPARATOR.split(classpathAttribute)) {
                try {
                    URL url = getClassPathEntry(jarFile, path);
                    if (url.getProtocol().equals("file")) {
                        builder.add((Object) toFile(url));
                    }
                } catch (MalformedURLException e) {
                    Logger logger2 = logger;
                    String valueOf = String.valueOf(path);
                    logger2.warning(valueOf.length() != 0 ? "Invalid Class-Path entry: ".concat(valueOf) : new String("Invalid Class-Path entry: "));
                }
            }
        }
        return builder.build();
    }

    static ImmutableMap<File, ClassLoader> getClassPathEntries(ClassLoader classloader) {
        LinkedHashMap<File, ClassLoader> entries = Maps.newLinkedHashMap();
        ClassLoader parent = classloader.getParent();
        if (parent != null) {
            entries.putAll(getClassPathEntries(parent));
        }
        UnmodifiableIterator<URL> it = getClassLoaderUrls(classloader).iterator();
        while (it.hasNext()) {
            URL url = it.next();
            if (url.getProtocol().equals("file")) {
                File file = toFile(url);
                if (!entries.containsKey(file)) {
                    entries.put(file, classloader);
                }
            }
        }
        return ImmutableMap.copyOf(entries);
    }

    private static ImmutableList<URL> getClassLoaderUrls(ClassLoader classloader) {
        if (classloader instanceof URLClassLoader) {
            return ImmutableList.copyOf((E[]) ((URLClassLoader) classloader).getURLs());
        }
        if (classloader.equals(ClassLoader.getSystemClassLoader())) {
            return parseJavaClassPath();
        }
        return ImmutableList.m41of();
    }

    static ImmutableList<URL> parseJavaClassPath() {
        ImmutableList.Builder<URL> urls = ImmutableList.builder();
        for (String entry : Splitter.m26on(StandardSystemProperty.PATH_SEPARATOR.value()).split(StandardSystemProperty.JAVA_CLASS_PATH.value())) {
            try {
                urls.add((Object) new File(entry).toURI().toURL());
            } catch (SecurityException e) {
                try {
                    urls.add((Object) new URL("file", (String) null, new File(entry).getAbsolutePath()));
                } catch (MalformedURLException e2) {
                    Logger logger2 = logger;
                    Level level = Level.WARNING;
                    String valueOf = String.valueOf(entry);
                    logger2.log(level, valueOf.length() != 0 ? "malformed classpath entry: ".concat(valueOf) : new String("malformed classpath entry: "), e2);
                }
            }
        }
        return urls.build();
    }

    static URL getClassPathEntry(File jarFile, String path) throws MalformedURLException {
        return new URL(jarFile.toURI().toURL(), path);
    }

    static String getClassName(String filename) {
        return filename.substring(0, filename.length() - CLASS_FILE_NAME_EXTENSION.length()).replace('/', '.');
    }

    static File toFile(URL url) {
        Preconditions.checkArgument(url.getProtocol().equals("file"));
        try {
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            return new File(url.getPath());
        }
    }
}
