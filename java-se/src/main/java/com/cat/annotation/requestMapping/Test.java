package com.cat.annotation.requestMapping;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * <p>Title: Test</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/23 20:42
 **/
public class Test {
    /**
     * 模拟Spring的component-scan的配置
     * 给定一个package，扫描package下面的所有类，并获得所有Class对象
     */
    private static List<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        //获取此线程的上下文ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //assert断言，如果classLoader != null为false，程序将会停止并抛出java.lang.AssertionError对象
        assert classLoader != null;

        //packageName的路径连接符可以是.或者/
        //返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。
        String path = packageName.replace('.', '/');

        //查找所有给定名称的资源（资源名称是以 '/' 分隔的标识资源的路径名称）
        //classLoader.getResources(path)返回资源的 URL 对象的枚举。如果找不到资源，则该枚举将为空。类加载器无权访问的资源不在此枚举中
        Enumeration<URL> resources = classLoader.getResources(path);

        List<File> dirs = new ArrayList<>();
        //当resources还有元素时
        while (resources.hasMoreElements()) {
            //获取下一个元素，并将其添加到disr文件列表中
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    /**
     * 通过目录来递归查找此目录下所有Class
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                //substring(int beginIndex, int endIndex) 【左闭右开】返回一个新字符串，它是此字符串的一个子字符串。
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //获得controller包下的所有Class对象
        List<Class> classes = getClasses("annotation");
        //已经成功获取该包下的所有class对象，包括接口
        Map<String, Class> handleMap = new HashMap<>(16);

        //1. 遍历controller包下的所有Class
        //2. 如果有@Controller注解,表明这是业务类,继续向下走
        //3. 获得Class的@RequestMapping注解的value值.
        //4. 遍历Class的所有Method,获得方法的@RequestMapping注解的value值,和Class的value值拼接,得到url.
        //5. url和Class加入map.
        classes.forEach(clazz -> {
            //从当前类上获取@Controller注解,如果不为null则,表明这是业务类,继续向下走
            Controller controller = (Controller) clazz.getAnnotation(Controller.class);
            if (controller == null) {
                return;
            }

            //在具备@Controller注解的业务类上获取@RequestMapping注解
            RequestMapping classRequestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            //若获取到了该注解
            if (classRequestMapping != null) {
                //获取该类上的注解路径
                final String classURI = classRequestMapping.value();

                //获取该类所有的方法
                Method[] methods = clazz.getMethods();
                //遍历这些方法
                for (Method method : methods) {
                    //尝试从该方法上获取@RequestMapping注解
                    RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                    //若成功获取
                    if (methodRequestMapping != null) {
                        //类路径+方法路径=完成的请求路径
                        String methodURI = classURI + methodRequestMapping.value();
                        handleMap.put(methodURI, clazz);
                    }
                }
            }
        });


        handleMap.forEach((s, clazz) -> System.out.println(s + " : " + clazz));
    }
}
