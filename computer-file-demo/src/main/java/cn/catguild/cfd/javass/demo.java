package cn.catguild.cfd.javass;

import javassist.*;

import java.io.IOException;

/**
 * @author liu.zhi
 * @date 2020/12/31 11:44
 */
public class demo {

    public static void main(String[] args) throws NotFoundException, IOException, CannotCompileException {
        ClassPool.getDefault().insertClassPath("C:\\Users\\Liuzhi\\Desktop\\jar\\aspose.pdf-16.12.0.jar");
        CtClass c2 = ClassPool.getDefault().getCtClass("com.aspose.pdf.z123");
        CtMethod[] ms = c2.getDeclaredMethods();
        System.out.println("方法执行");
        for (CtMethod c : ms) {
            CtClass[] ps = c.getParameterTypes();

            if (c.getName().equals("m1") && ps.length == 2
                    && ps[0].getName().equals("org.w3c.dom.Node")
                    && ps[1].getName().equals("org.w3c.dom.Node")){
                String aaa = "{return;}";
                System.out.println("if执行");
                System.out.println(aaa);
                c.setBody(aaa);
                c2.writeFile();
            }
        }
        c2.writeFile();
    }

}
